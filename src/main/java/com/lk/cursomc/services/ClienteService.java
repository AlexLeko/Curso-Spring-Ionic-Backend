package com.lk.cursomc.services;

import com.lk.cursomc.domain.Cidade;
import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.domain.Endereco;
import com.lk.cursomc.domain.enums.Perfil;
import com.lk.cursomc.domain.enums.TipoCliente;
import com.lk.cursomc.dto.ClienteDTO;
import com.lk.cursomc.dto.ClienteNewDTO;
import com.lk.cursomc.repositories.ClienteRepository;
import com.lk.cursomc.repositories.EnderecoRepository;
import com.lk.cursomc.security.UserSS;
import com.lk.cursomc.services.exceptions.AuthorizationException;
import com.lk.cursomc.services.exceptions.DataIntegrityException;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository _clienteRepository;

    @Autowired
    private EnderecoRepository _enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;



    public Cliente find(Integer id){

        // Valida consulta, somente para o mesmo Cliente.
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()) ) {
            throw new AuthorizationException("Acesso Negado !");
        }

        Optional<Cliente> cliente = _clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente){
        cliente.setId(null);
        cliente = _clienteRepository.save(cliente);

        _enderecoRepository.saveAll(cliente.getEnderecos());

        return cliente;
    }

    public Cliente update(Cliente cliente){
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);

        return _clienteRepository.save(cliente);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public void delete(Integer id){
        find(id);

        try{
            _clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não é possível excluir um cliente com pedidos em aberto.");
        }
    }

    public List<Cliente> findAll(){
        return _clienteRepository.findAll();
    }

    public Cliente findByEmail(String email) {

        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername()) ) {
            throw new AuthorizationException("Acesso Negado");
        }

        Cliente cliente = _clienteRepository.findByEmail(email);
        if(cliente == null) {
            throw new ObjectNotFoundException("Cliente não encontrado! ID: " + user.getId() + ", Tipo: " + Cliente.class.getName());
        }

        return cliente;
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return _clienteRepository.findAll(pageRequest);
    }


    public Cliente fromDTO(ClienteDTO dto){
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO dto){
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfCnpj(),
                TipoCliente.toEnum(dto.getTipo()), _passwordEncoder.encode(dto.getSenha()));
        Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(dto.getTelefone1());

        if (dto.getTelefone2() != null){
            cliente.getTelefones().add(dto.getTelefone2());
        }
        if (dto.getTelefone3() != null){
            cliente.getTelefones().add(dto.getTelefone3());
        }

        return cliente;
    }


    public URI uploadProfilePicture(MultipartFile multipartFile) {
        // Recuperar o usuario logado
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso Negado !");
        }

        // Define o nome do arquivo no PADRÃO do prefix;
        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

        // Tratando a imagem.
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String filename = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), filename, "image");

//        URI uri = s3Service.uploadFile(multipartFile);
//
//        Cliente cliente = _clienteRepository.findById(user.getId()).get();
//        cliente.setImageURI(uri.toString());
//        _clienteRepository.save(cliente);
//
//        return uri;
    }


}

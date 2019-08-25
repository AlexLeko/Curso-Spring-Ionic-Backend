package com.lk.cursomc.services;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.dto.CategoriaDTO;
import com.lk.cursomc.dto.ClienteDTO;
import com.lk.cursomc.repositories.ClienteRepository;
import com.lk.cursomc.services.exceptions.DataIntegrityException;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository _repository;

    public Cliente find(Integer id){
        Optional<Cliente> cliente = _repository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Cliente.class.getName()));
    }

//    public Cliente insert(Cliente cliente){
//        cliente.setId(null);
//        return _repository.save(cliente);
//    }

    public Cliente update(Cliente cliente){
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);

        return _repository.save(cliente);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public void delete(Integer id){
        find(id);

        try{
            _repository.deleteById(id);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não é possível excluir um cliente com pedidos em aberto.");
        }
    }

    public List<Cliente> findAll(){
        return _repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return _repository.findAll(pageRequest);
    }


    public Cliente fromDTO(ClienteDTO dto){
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
    }



}

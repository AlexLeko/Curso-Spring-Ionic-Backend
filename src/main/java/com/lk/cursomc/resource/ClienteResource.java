package com.lk.cursomc.resource;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.dto.CategoriaDTO;
import com.lk.cursomc.dto.ClienteDTO;
import com.lk.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService _service;


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = _service.find(id);
        return ResponseEntity.ok().body(cliente);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO){
//        Categoria categoria = _service.fromDTO(categoriaDTO);
//        categoria = _service.insert(categoria);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(categoria.getId())
//                .toUri();
//
//        return ResponseEntity.created(uri).build(); // retorna o endpoint criado.
//    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = _service.fromDTO(clienteDTO);

        cliente.setId(id);
        cliente = _service.update(cliente);

        return ResponseEntity.noContent().build();  // status: 204 created
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        _service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = _service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO); // status: 200 OK
    }


    @RequestMapping(value = "/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> list = _service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listDTO); // status: 200 OK
    }


}

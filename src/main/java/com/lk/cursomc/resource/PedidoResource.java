package com.lk.cursomc.resource;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.domain.Pedido;
import com.lk.cursomc.dto.CategoriaDTO;
import com.lk.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService _service;


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        Pedido pedido = _service.find(id);
        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido){

        pedido = _service.insert(pedido);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).build(); // retorna o endpoint criado.
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "instance") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        Page<Pedido> list = _service.findPage(page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(list); // status: 200 OK
    }

}

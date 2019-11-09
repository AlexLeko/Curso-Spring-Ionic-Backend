package com.lk.cursomc.resource;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.dto.CategoriaDTO;
import com.lk.cursomc.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService _service;


    @ApiOperation(value = "Busca categoria por Id")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria categoria = _service.find(id);
        return ResponseEntity.ok().body(categoria); // status: 200 OK
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Inserir Categoria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = _service.fromDTO(categoriaDTO);
        categoria = _service.insert(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(categoria.getId())
                    .toUri();

        return ResponseEntity.created(uri).build(); // retorna o endpoint criado.
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Atualizar Categoria")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
        Categoria categoria = _service.fromDTO(categoriaDTO);

        categoria.setId(id);
        categoria = _service.update(categoria);

        return ResponseEntity.noContent().build();  // status: 204 | criado com sucesso, sem retorno na resposta.
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Excluir Categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possivel excluir uma categoria que possui produtos"),
            @ApiResponse(code = 404, message = "Código inexistente")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        _service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Recuperar Todas as Categorias")
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = _service.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO); // status: 200 OK
    }

    @ApiOperation(value = "Consultar Categorias com Paginação")
    @RequestMapping(value = "/page", method=RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Categoria> list = _service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));

        return ResponseEntity.ok().body(listDTO); // status: 200 OK
    }


}

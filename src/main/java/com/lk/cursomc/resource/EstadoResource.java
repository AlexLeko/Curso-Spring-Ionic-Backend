package com.lk.cursomc.resource;

import com.lk.cursomc.domain.Cidade;
import com.lk.cursomc.domain.Estado;
import com.lk.cursomc.dto.CidadeDTO;
import com.lk.cursomc.dto.EstadoDTO;
import com.lk.cursomc.services.CidadeService;
import com.lk.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {

        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDTO = list.stream().map(
                                    estado -> new EstadoDTO(estado))
                                        .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {

        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDTO = list.stream().map(
                                        cidade -> new CidadeDTO(cidade))
                                        .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }


}

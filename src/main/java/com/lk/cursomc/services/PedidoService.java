package com.lk.cursomc.services;

import com.lk.cursomc.domain.Pedido;
import com.lk.cursomc.repositories.PedidoRepository;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository _repository;

    public Pedido find(Integer id){
        Optional<Pedido> pedido = _repository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Pedido.class.getName()));
    }



}

package com.lk.cursomc.services;

import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.repositories.ClienteRepository;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}

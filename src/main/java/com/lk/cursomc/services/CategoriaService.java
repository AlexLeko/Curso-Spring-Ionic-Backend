package com.lk.cursomc.services;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.repositories.CategoriaRepository;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository _repository;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria = _repository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Categoria.class.getName()));
    }



}

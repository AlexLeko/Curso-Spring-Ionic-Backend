package com.lk.cursomc.services;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository _repository;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria = _repository.findById(id);
        return categoria.orElse(null);
    }



}

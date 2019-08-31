package com.lk.cursomc.services;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.domain.Produto;
import com.lk.cursomc.repositories.CategoriaRepository;
import com.lk.cursomc.repositories.ProdutoRepository;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository _repository;
    @Autowired
    private CategoriaRepository _categoriaRepository;


    public Produto find(Integer id){
        Optional<Produto> produto = _repository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Produto.class.getName()));
    }

    public Page<Produto> search(
            String nome,
            List<Integer> ids,
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Categoria> categorias = _categoriaRepository.findAllById(ids);

        //return _repository.search(nome, categorias, pageRequest);

        return _repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }

}

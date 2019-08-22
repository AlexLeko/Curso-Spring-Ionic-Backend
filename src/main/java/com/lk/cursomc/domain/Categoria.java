package com.lk.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @JsonManagedReference
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();



    // ===================
    //     CONSTRUCTORS
    // ===================

    public Categoria() {
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }



    // ===================
    // GETTERS AND SETTERS
    // ===================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }



    // ===================
    // EQUALS AND HASHCODE
    // ===================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

package com.lk.cursomc;

import com.lk.cursomc.domain.Categoria;
import com.lk.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}




	// Mock Categoria

	@Autowired
	private CategoriaRepository _categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        _categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}




}
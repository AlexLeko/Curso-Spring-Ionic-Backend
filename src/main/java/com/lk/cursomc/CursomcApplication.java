package com.lk.cursomc;

import com.lk.cursomc.domain.*;
import com.lk.cursomc.domain.enums.TipoCliente;
import com.lk.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository _categoriaRepository;
	@Autowired
	private ProdutoRepository _produtoRepository;
	@Autowired
	private CidadeRepository _cidadeRepository;
	@Autowired
	private EstadoRepository _estadoRepository;
	@Autowired
	private ClienteRepository _clienteRepository;
	@Autowired
	private EnderecoRepository _enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}


	// Mocks H2

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// RELACIONAMENTO
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		_categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		_produtoRepository.saveAll(Arrays.asList(p1, p2, p3));


		// CIDADE X ESTADO
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		_estadoRepository.saveAll(Arrays.asList(est1, est2));
		_cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


		// CLIENTE - TIPO - TELEFONES - ENDEREÇOS

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@net.com", "123456789", TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("1111111111", "22222222222"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "apt 303", "Jardim", "42432433", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "42432433", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		_clienteRepository.saveAll(Arrays.asList(cli1));
		_enderecoRepository.saveAll(Arrays.asList(end1, end2));

	}




}
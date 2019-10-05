package com.lk.cursomc.services;

import com.lk.cursomc.domain.*;
import com.lk.cursomc.domain.enums.EstadoPagamento;
import com.lk.cursomc.domain.enums.Perfil;
import com.lk.cursomc.domain.enums.TipoCliente;
import com.lk.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

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
    @Autowired
    private PedidoRepository _pedidoRepository;
    @Autowired
    private PagamentoRepository _pagamentoRepository;
    @Autowired
    private ItemPedidoRepository _itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;



    public void instantiateTestDatabase() throws ParseException {

        // ==================
        // 		Mocks H2
        // ==================

        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");
        Categoria categoria3 = new Categoria(null, "Cama Mesa e Banho");
        Categoria categoria4 = new Categoria(null, "Eletrônicos");
        Categoria categoria5 = new Categoria(null, "Jardinagem");
        Categoria categoria6 = new Categoria(null, "Decoração");
        Categoria categoria7 = new Categoria(null, "Perfumaria");
        Categoria categoria8 = new Categoria(null, "Vestuário");


        Produto produto_1 = new Produto(null, "Computador", 2000.00);
        Produto produto_2 = new Produto(null, "Impressora", 800.00);
        Produto produto_3 = new Produto(null, "Mouse", 80.00);
        Produto produto_4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto produto_5 = new Produto(null, "Toalha", 50.00);
        Produto produto_6 = new Produto(null, "Colcha", 200.00);
        Produto produto_7 = new Produto(null, "TV Color", 1200.00);
        Produto produto_8 = new Produto(null, "Roçadeira", 800.00);
        Produto produto_9 = new Produto(null, "Abajour", 100.00);
        Produto produto_10 = new Produto(null, "Pendente", 180.00);
        Produto produto_11 = new Produto(null, "Shampoo", 90.00);

        // Infinity Scroll
        Produto produto_12 = new Produto(null, "Produto 12", 10.00);
        Produto produto_13 = new Produto(null, "Produto 13", 10.00);
        Produto produto_14 = new Produto(null, "Produto 14", 10.00);
        Produto produto_15 = new Produto(null, "Produto 15", 10.00);
        Produto produto_16 = new Produto(null, "Produto 16", 10.00);
        Produto produto_17 = new Produto(null, "Produto 17", 10.00);
        Produto produto_18 = new Produto(null, "Produto 18", 10.00);
        Produto produto_19 = new Produto(null, "Produto 19", 10.00);
        Produto produto_20 = new Produto(null, "Produto 20", 10.00);
        Produto produto_21 = new Produto(null, "Produto 21", 10.00);
        Produto produto_22 = new Produto(null, "Produto 22", 10.00);
        Produto produto_23 = new Produto(null, "Produto 23", 10.00);
        Produto produto_24 = new Produto(null, "Produto 24", 10.00);
        Produto produto_25 = new Produto(null, "Produto 25", 10.00);
        Produto produto_26 = new Produto(null, "Produto 26", 10.00);
        Produto produto_27 = new Produto(null, "Produto 27", 10.00);
        Produto produto_28 = new Produto(null, "Produto 28", 10.00);
        Produto produto_29 = new Produto(null, "Produto 29", 10.00);
        Produto produto_30 = new Produto(null, "Produto 30", 10.00);
        Produto produto_31 = new Produto(null, "Produto 31", 10.00);
        Produto produto_32 = new Produto(null, "Produto 32", 10.00);
        Produto produto_33 = new Produto(null, "Produto 33", 10.00);
        Produto produto_34 = new Produto(null, "Produto 34", 10.00);
        Produto produto_35 = new Produto(null, "Produto 35", 10.00);
        Produto produto_36 = new Produto(null, "Produto 36", 10.00);
        Produto produto_37 = new Produto(null, "Produto 37", 10.00);
        Produto produto_38 = new Produto(null, "Produto 38", 10.00);
        Produto produto_39 = new Produto(null, "Produto 39", 10.00);
        Produto produto_40 = new Produto(null, "Produto 40", 10.00);
        Produto produto_41 = new Produto(null, "Produto 41", 10.00);
        Produto produto_42 = new Produto(null, "Produto 42", 10.00);
        Produto produto_43 = new Produto(null, "Produto 43", 10.00);
        Produto produto_44 = new Produto(null, "Produto 44", 10.00);
        Produto produto_45 = new Produto(null, "Produto 45", 10.00);
        Produto produto_46 = new Produto(null, "Produto 46", 10.00);
        Produto produto_47 = new Produto(null, "Produto 47", 10.00);
        Produto produto_48 = new Produto(null, "Produto 48", 10.00);
        Produto produto_49 = new Produto(null, "Produto 49", 10.00);
        Produto produto_50 = new Produto(null, "Produto 50", 10.00);
        Produto produto_51 = new Produto(null, "Produto 51", 10.00);
        Produto produto_52 = new Produto(null, "Produto 52", 10.00);


        // RELACIONAMENTO
        categoria1.getProdutos().addAll(Arrays.asList(
                produto_12, produto_13, produto_14, produto_15, produto_16, produto_17, produto_18, produto_19, produto_20,
                produto_21, produto_22, produto_23, produto_24, produto_25, produto_26, produto_27, produto_28, produto_29, produto_30,
                produto_31, produto_32, produto_33, produto_34, produto_35, produto_36, produto_37, produto_38, produto_39, produto_40,
                produto_41, produto_42, produto_43, produto_44, produto_45, produto_46, produto_47, produto_48, produto_49, produto_50,
                produto_51, produto_52));

        //categoria1.getProdutos().addAll(Arrays.asList(produto_1, produto_2, produto_3));
        categoria2.getProdutos().addAll(Arrays.asList(produto_2, produto_4));
        categoria3.getProdutos().addAll(Arrays.asList(produto_5, produto_6));
        categoria4.getProdutos().addAll(Arrays.asList(produto_1, produto_2, produto_3, produto_7));
        categoria5.getProdutos().addAll(Arrays.asList(produto_2, produto_8));
        categoria6.getProdutos().addAll(Arrays.asList(produto_2, produto_9, produto_10));
        categoria7.getProdutos().addAll(Arrays.asList(produto_2, produto_11));


        produto_1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
        produto_2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
        produto_3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
        produto_4.getCategorias().addAll(Arrays.asList(categoria2));
        produto_5.getCategorias().addAll(Arrays.asList(categoria3));
        produto_6.getCategorias().addAll(Arrays.asList(categoria3));
        produto_7.getCategorias().addAll(Arrays.asList(categoria4));
        produto_8.getCategorias().addAll(Arrays.asList(categoria5));
        produto_9.getCategorias().addAll(Arrays.asList(categoria6));
        produto_10.getCategorias().addAll(Arrays.asList(categoria6));
        produto_11.getCategorias().addAll(Arrays.asList(categoria7));

        // infinity scroll
        produto_12.getCategorias().add(categoria1);
        produto_13.getCategorias().add(categoria1);
        produto_14.getCategorias().add(categoria1);
        produto_15.getCategorias().add(categoria1);
        produto_16.getCategorias().add(categoria1);
        produto_17.getCategorias().add(categoria1);
        produto_18.getCategorias().add(categoria1);
        produto_19.getCategorias().add(categoria1);
        produto_20.getCategorias().add(categoria1);
        produto_21.getCategorias().add(categoria1);
        produto_22.getCategorias().add(categoria1);
        produto_23.getCategorias().add(categoria1);
        produto_24.getCategorias().add(categoria1);
        produto_25.getCategorias().add(categoria1);
        produto_26.getCategorias().add(categoria1);
        produto_27.getCategorias().add(categoria1);
        produto_28.getCategorias().add(categoria1);
        produto_29.getCategorias().add(categoria1);
        produto_30.getCategorias().add(categoria1);
        produto_31.getCategorias().add(categoria1);
        produto_32.getCategorias().add(categoria1);
        produto_33.getCategorias().add(categoria1);
        produto_34.getCategorias().add(categoria1);
        produto_35.getCategorias().add(categoria1);
        produto_36.getCategorias().add(categoria1);
        produto_37.getCategorias().add(categoria1);
        produto_38.getCategorias().add(categoria1);
        produto_39.getCategorias().add(categoria1);
        produto_40.getCategorias().add(categoria1);
        produto_41.getCategorias().add(categoria1);
        produto_42.getCategorias().add(categoria1);
        produto_43.getCategorias().add(categoria1);
        produto_44.getCategorias().add(categoria1);
        produto_45.getCategorias().add(categoria1);
        produto_46.getCategorias().add(categoria1);
        produto_47.getCategorias().add(categoria1);
        produto_48.getCategorias().add(categoria1);
        produto_49.getCategorias().add(categoria1);
        produto_50.getCategorias().add(categoria1);
        produto_51.getCategorias().add(categoria1);
        produto_52.getCategorias().add(categoria1);


        _categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3,
                categoria4, categoria5, categoria6, categoria7, categoria8));
        _produtoRepository.saveAll(Arrays.asList(
                produto_1, produto_2, produto_3, produto_4, produto_5, produto_6, produto_7, produto_8, produto_9 , produto_10,
                produto_11, produto_12, produto_13, produto_14, produto_15, produto_16, produto_17, produto_18, produto_19, produto_20,
                produto_21, produto_22, produto_23, produto_24, produto_25, produto_26, produto_27, produto_28, produto_29, produto_30,
                produto_31, produto_32, produto_33, produto_34, produto_35, produto_36, produto_37, produto_38, produto_39, produto_40,
                produto_41, produto_42, produto_43, produto_44, produto_45, produto_46, produto_47, produto_48, produto_49, produto_50,
                produto_51, produto_52));


        // CIDADE X ESTADO

        Estado estado_1 = new Estado(null, "Minas Gerais");
        Estado estado_2 = new Estado(null, "São Paulo");

        Cidade cidade_1 = new Cidade(null, "Uberlândia", estado_1);
        Cidade cidade_2 = new Cidade(null, "São Paulo", estado_2);
        Cidade cidade_3 = new Cidade(null, "Campinas", estado_2);

        estado_1.getCidades().addAll(Arrays.asList(cidade_1));
        estado_2.getCidades().addAll(Arrays.asList(cidade_2, cidade_3));

        _estadoRepository.saveAll(Arrays.asList(estado_1, estado_2));
        _cidadeRepository.saveAll(Arrays.asList(cidade_1, cidade_2, cidade_3));


        // CLIENTE - TIPO - TELEFONES - ENDEREÇOS

        Cliente cliente_1 = new Cliente(null, "Maria Silva", "lk.alexds@hotmail.com", "88681638025",
                TipoCliente.PESSOA_FISICA, _passwordEncoder.encode("123456"));
        cliente_1.getTelefones().addAll(Arrays.asList("1111111111", "22222222222"));

        Cliente cliente_2 = new Cliente(null, "Felipe Perez", "lk.alexds@gmail.com", "45562451093",
                TipoCliente.PESSOA_FISICA, _passwordEncoder.encode("123456"));

        cliente_2.getTelefones().addAll(Arrays.asList("1699168090", "1697141010"));
        cliente_2.addPerfis(Perfil.ADMIN);

        Endereco endereco_1 = new Endereco(null, "Rua Flores", "300", "apt 303", "Jardim", "42432433", cliente_1, cidade_1);
        Endereco endereco_2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "42432433", cliente_1, cidade_2);
        Endereco endereco_3 = new Endereco(null, "Avenida Florinda", "71", null, "Vila", "10111000", cliente_2, cidade_2);

        cliente_1.getEnderecos().addAll(Arrays.asList(endereco_1, endereco_2));
        cliente_2.getEnderecos().addAll(Arrays.asList(endereco_3));

        _clienteRepository.saveAll(Arrays.asList(cliente_1, cliente_2));
        _enderecoRepository.saveAll(Arrays.asList(endereco_1, endereco_2, endereco_3));


        // PEDIDO - PAGAMENTO

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido_1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente_1, endereco_1);
        Pedido pedido_2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente_1, endereco_2);

        Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedido_1, 6);
        pedido_1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedido_2, null, sdf.parse("20/10/2017 00:00"));
        pedido_2.setPagamento(pagto2);

        cliente_1.getPedidos().addAll(Arrays.asList(pedido_1, pedido_2));

        _pedidoRepository.saveAll(Arrays.asList(pedido_1, pedido_2));
        _pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));


        // ITEM-PEDIDO

        ItemPedido itemPed_1 = new ItemPedido(pedido_1, produto_1, 0.00, 1, 2000.00);
        ItemPedido itemPed_2 = new ItemPedido(pedido_1, produto_3, 0.00, 2, 80.00);
        ItemPedido itemPed_3 = new ItemPedido(pedido_2, produto_2, 100.00, 1, 800.00);

        pedido_1.getItens().addAll(Arrays.asList(itemPed_1, itemPed_2));
        pedido_2.getItens().addAll(Arrays.asList(itemPed_3));

        produto_1.getItens().addAll(Arrays.asList(itemPed_1));
        produto_2.getItens().addAll(Arrays.asList(itemPed_3));
        produto_3.getItens().addAll(Arrays.asList(itemPed_2));

        _itemPedidoRepository.saveAll(Arrays.asList(itemPed_1, itemPed_2, itemPed_3));

    }
}

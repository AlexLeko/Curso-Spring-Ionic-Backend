package com.lk.cursomc.services;

import com.lk.cursomc.domain.ItemPedido;
import com.lk.cursomc.domain.PagamentoBoleto;
import com.lk.cursomc.domain.Pedido;
import com.lk.cursomc.domain.enums.EstadoPagamento;
import com.lk.cursomc.repositories.ItemPedidoRepository;
import com.lk.cursomc.repositories.PagamentoRepository;
import com.lk.cursomc.repositories.PedidoRepository;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository _repository;

    @Autowired
    private BoletoService _boletoService;
    @Autowired
    private ProdutoService _produtoService;
    @Autowired
    private ClienteService _clienteService;

    @Autowired
    private PagamentoRepository _pagamentoRepository;
    @Autowired
    private ItemPedidoRepository _itemPedidoRepository;

    @Autowired
    private EmailService _emailService;



    public Pedido find(Integer id){
        Optional<Pedido> pedido = _repository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + " , TIPO: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido pedido) {

        pedido.setId(null);
        pedido.setInstance(new Date());
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        pedido.setCliente(_clienteService.find(pedido.getCliente().getId()));

        if(pedido.getPagamento() instanceof PagamentoBoleto) {
            PagamentoBoleto pagamento = (PagamentoBoleto) pedido.getPagamento();
            _boletoService.preencherPagamentoBoleto(pagamento, pedido.getInstance());
        }

        pedido = _repository.save(pedido);

        _pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido item : pedido.getItens()) {
            item.setDesconto(0.0);

            item.setProduto(_produtoService.find(item.getProduto().getId()));
            item.setPreco(item.getProduto().getPreco());

            item.setPedido(pedido);
        }

        _itemPedidoRepository.saveAll(pedido.getItens());

        // teste corpo do E-mail
        // System.out.println(pedido);

        // teste mock envio de e-mail
        // _emailService.sendOrderConfirmationEmail(pedido);

        // teste envio de e-mail HTML
        _emailService.sendOrderConfirmationHtmlEmail(pedido);

        return pedido;
    }

}

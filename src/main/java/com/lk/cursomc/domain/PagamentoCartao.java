package com.lk.cursomc.domain;

import com.lk.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
public class PagamentoCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Integer numeroParcelas;


    // ===================
    //     CONSTRUCTORS
    // ===================

    public PagamentoCartao() {
    }

    public PagamentoCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    // ===================
    // GETTERS AND SETTERS
    // ===================

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }



}

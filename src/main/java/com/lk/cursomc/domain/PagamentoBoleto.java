package com.lk.cursomc.domain;

import com.lk.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Date dataPagamento;
    private Date dataVencimento;



    // ===================
    //     CONSTRUCTORS
    // ===================

    public PagamentoBoleto() {
    }

    public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataPagamento, Date dataVencimento) {
        super(id, estadoPagamento, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    // ===================
    // GETTERS AND SETTERS
    // ===================

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }



}

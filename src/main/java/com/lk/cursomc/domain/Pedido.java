package com.lk.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instance;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();


    // ===================
    //     CONSTRUCTORS
    // ===================

    public Pedido() {
    }

    public Pedido(Integer id, Date instance, Cliente cliente, Endereco enderecoEntrega) {
        this.id = id;
        this.instance = instance;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }

    public double getValorTotal(){
        double soma = 0.0;
        for (ItemPedido item : itens){
            soma = soma + item.getSubTotal();
        }
        return soma;
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

    public Date getInstance() {
        return instance;
    }

    public void setInstance(Date instance) {
        this.instance = instance;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }


    // ===================
    // EQUALS AND HASHCODE
    // ===================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        final StringBuilder sb = new StringBuilder();

        sb.append("Pedido Número: ").append(getId());
        sb.append(", Instante: ").append(sdf.format(getInstance()));
        sb.append(", Cliente: ").append(getCliente().getNome());
        sb.append(", Situação do Pagamento: ").append(getPagamento().getEstadoPagamento().getDescricao());

        sb.append("\nDetalhes:\n");
        for (ItemPedido item : getItens()){
            sb.append(item.toString());
        }

        sb.append("Valor Total: ").append(nf.format(getValorTotal()));

        return sb.toString();
    }
}

package com.lk.cursomc.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(2, "Cancelado");

    private int codigo;
    private String descricao;


    // ===================
    //     CONSTRUCTORS
    // ===================

    EstadoPagamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }


    // ===================
    //      GETTERS
    // ===================

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }


    // ===================
    //     COMPLEMENTS
    // ===================

    public static EstadoPagamento toEnum(Integer cod){

        if (cod == null) return null;

        for (EstadoPagamento cli : EstadoPagamento.values()){
            if (cod.equals(cli.getCodigo())){
                return cli;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}

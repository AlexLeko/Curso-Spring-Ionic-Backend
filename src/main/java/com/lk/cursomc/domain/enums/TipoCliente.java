package com.lk.cursomc.domain.enums;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;


    // ===================
    //     CONSTRUCTORS
    // ===================

    TipoCliente(Integer codigo, String descricao) {
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

    public static TipoCliente toEnum(Integer cod){

        if (cod == null) return null;

        for (TipoCliente cli : TipoCliente.values()){
            if (cod.equals(cli.getCodigo())){
                return cli;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}

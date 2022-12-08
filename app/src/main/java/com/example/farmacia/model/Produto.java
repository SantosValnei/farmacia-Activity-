package com.example.farmacia.model;

import java.io.Serializable;

public class Produto implements Serializable {

    private Long id;
    private String nomeProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}

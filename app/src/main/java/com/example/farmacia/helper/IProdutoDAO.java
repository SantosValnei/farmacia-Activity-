package com.example.farmacia.helper;

import com.example.farmacia.model.Produto;

import java.util.List;

public interface IProdutoDAO {

    public boolean salvar(Produto produto);
    public boolean atualizar(Produto produto);
    public boolean deletar(Produto produto);
    public List<Produto> listar();

}

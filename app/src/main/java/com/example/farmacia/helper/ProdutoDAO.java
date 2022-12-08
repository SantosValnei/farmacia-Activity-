package com.example.farmacia.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.farmacia.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements  IProdutoDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ProdutoDAO(Context context) {
        DbHelper db = new DbHelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Produto produto) {

        ContentValues cv = new ContentValues();
        cv.put( "nome", produto.getNomeProduto() );

        try {
            escreve.insert(DbHelper.TABELA_PRODUTOS, null, cv );
            Log.i( "INFO", "Produto salvo com sucesso");
        } catch (Exception e) {
            Log.e( "INFO", "Erro ao salvar produto " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Produto produto) {
        return false;
    }

    @Override
    public boolean deletar(Produto produto) {
        return false;
    }

    @Override
    public List<Produto> listar() {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_PRODUTOS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() ){

            Produto produto = new Produto();

            Long id = c.getLong( c.getColumnIndex( "id") );
            String nomeProduto = c.getString( c.getColumnIndex( "nome") );

            produto.setId( id );
            produto.setNomeProduto( nomeProduto );

            produtos.add( produto );

        }

        return  produtos;

    }
}

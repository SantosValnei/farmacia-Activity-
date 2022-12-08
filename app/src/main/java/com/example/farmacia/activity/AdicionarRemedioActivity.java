package com.example.farmacia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.farmacia.R;
import com.example.farmacia.helper.ProdutoDAO;
import com.example.farmacia.model.Produto;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarRemedioActivity extends AppCompatActivity {

    private TextInputEditText editProduto;
    private Produto produtoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_remedio);

        editProduto = findViewById(R.id.TextProduto);

        //Recuperar produto, caso seja edicao
        produtoAtual = (Produto) getIntent().getSerializableExtra("produtoSelecionado");

        //configurar produto na caixa de texto
        if( produtoAtual != null ) {
            editProduto.setText( produtoAtual.getNomeProduto() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_remedio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemSalvar:
                //Executa acao para o item salvar
                ProdutoDAO produtoDAO = new ProdutoDAO( getApplicationContext() );

                String nomeProduto = editProduto.getText().toString();
                if ( !nomeProduto.isEmpty() ){
                    Produto produto = new Produto();
                    produto.setNomeProduto(nomeProduto);
                    produtoDAO.salvar( produto );
                    finish();
                }


                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

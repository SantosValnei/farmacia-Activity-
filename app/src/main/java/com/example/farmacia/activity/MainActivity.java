package com.example.farmacia.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.example.farmacia.R;
import com.example.farmacia.adapter.ProdutoAdapter;
import com.example.farmacia.helper.DbHelper;
import com.example.farmacia.helper.ProdutoDAO;
import com.example.farmacia.helper.RecyclerItemClickListener;
import com.example.farmacia.model.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    private List<Produto> listaProdutos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Configurar recycler
        recyclerView = findViewById(R.id.recyclerView);

        //adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //Recuperar produto para edicao
                                Produto produtoSelecionado = listaProdutos.get( position );

                                //Envia produto para tela adicionar produto
                                Intent intent = new Intent(MainActivity.this, AdicionarRemedioActivity.class);
                                intent.putExtra("produtoSelecionado", produtoSelecionado);

                                startActivity( intent );

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i( "clique", "onLongItemClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdicionarRemedioActivity.class);
                startActivity( intent );

            }
        });
    }

    public void carregarListaProdutos(){

        //listar produtos
        ProdutoDAO produtoDAO = new ProdutoDAO( getApplicationContext() );
        listaProdutos = produtoDAO.listar();

        /*
        Exibe lista de tarefas no Recyclerview
         */

        //configurar um adapter
        produtoAdapter = new ProdutoAdapter( listaProdutos );


        //configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( produtoAdapter );

    }

    @Override
    protected void onStart() {
        carregarListaProdutos();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.farmacia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmacia.R;
import com.example.farmacia.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private List<Produto> listaProdutos;

    public ProdutoAdapter(List<Produto> lista) {
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemProduto = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.lista_produto_adapter, parent, false);

        return new MyViewHolder(itemProduto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Produto produto = listaProdutos.get(position);
        holder.produto.setText( produto.getNomeProduto() );

    }

    @Override
    public int getItemCount() {
        return this.listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView produto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            produto = itemView.findViewById(R.id.textProduto);
        }
    }

}

package com.example.will.peladacerta;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aluno on 18/04/2017.
 */
public class PeladaAdapter extends RecyclerView.Adapter<ListaViewHolder>{

    private List<Pelada> peladaList;

    public PeladaAdapter(List<Pelada> pelada){
        peladaList = pelada;

    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ListaViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.pelada_linha, parent, false));
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        holder.textView_titulo.setText(peladaList.get(position).getTitulo());
        holder.textView_endereco.setText(peladaList.get(position).getBairro());
        holder.textView_vagasDisponiveis.setText(Integer.toString(peladaList.get(position).getVagasDisponiveis()));

    }

    @Override
    public int getItemCount() {

        return peladaList.size();
    }


}

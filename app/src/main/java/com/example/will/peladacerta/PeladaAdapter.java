package com.example.will.peladacerta;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aluno on 18/04/2017.
 */
public class PeladaAdapter extends RecyclerView.Adapter<ListaViewHolder>{

    private List<Pelada> peladaList;

    public PeladaAdapter(List<Pelada> pelada){

    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return peladaList.size();
    }


}

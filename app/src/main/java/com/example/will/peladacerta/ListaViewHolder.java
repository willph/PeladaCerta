package com.example.will.peladacerta;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aluno on 12/04/2017.
 */

public class ListaViewHolder extends RecyclerView.ViewHolder{

    TextView textView_titulo;
    TextView textView_endereco;
    TextView textView_vagasDisponiveis;


    public ListaViewHolder(View itemView) {
        super(itemView);

        textView_titulo = (TextView) itemView.findViewById(R.id.peladaActivity_textView_tituloDaPelada);
        textView_endereco = (TextView) itemView.findViewById(R.id.peladaActivity_textView_endereco);
        textView_vagasDisponiveis = (TextView) itemView.findViewById(R.id.peladaActivity_textView_vagasDisponiveis);
    }
}

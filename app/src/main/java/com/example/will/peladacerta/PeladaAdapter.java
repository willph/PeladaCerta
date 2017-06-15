package com.example.will.peladacerta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by aluno on 18/04/2017.
 */
public class PeladaAdapter extends RecyclerView.Adapter<ListaViewHolder>{

//    private List<Pelada> peladaList;
    private List<PeladaModel> peladaList;
    private Context context;


//    public PeladaAdapter(List<Pelada> pelada){
//        peladaList = pelada;
//    }

    public PeladaAdapter(List<PeladaModel> pelada, Context context){
        this.peladaList = pelada;
        this.context = context;

    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ListaViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.pelada_linha, parent, false));
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {



        holder.textView_titulo.setText(peladaList.get(position).getTitle());
        holder.textView_endereco.setText(peladaList.get(position).getAddress_full());
//        holder.textView_vagasDisponiveis.setText(Integer.toString(peladaList.get(position).getVagasDisponiveis()));
//        Bitmap bitmap = BitmapFactory.decodeStream(peladaList.get(position).getImagem());
        Glide.with(context).load(peladaList.get(position).getImgUrl()).into(holder.imageView_imagemPelada);
//        holder.imageView_imagemPelada.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {

        return peladaList.size();
    }


}

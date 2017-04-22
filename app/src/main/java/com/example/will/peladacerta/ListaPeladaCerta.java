package com.example.will.peladacerta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaPeladaCerta extends AppCompatActivity {

    RecyclerView recyclerView;
    PeladaAdapter peladaAdapter;
    List<Pelada> peladaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pelada_certa);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_layout_recycler);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        peladaList = new ArrayList<>();
        peladaList.add(new Pelada(1,"Pelada do Fragoso", "Muribeca", "","","", "", "",2,1));
        peladaList.add(new Pelada(2,"Pelada do Jonh", "Recife", "","","", "", "",2,1));
        peladaList.add(new Pelada(3,"Pelada do Carlos", "Suvado da Cobra", "","","", "", "",2,1));
        peladaList.add(new Pelada(4,"Pelada do Cadena", "EntraPusso", "","","", "", "",2,1));
        peladaList.add(new Pelada(5,"Pelada do Adriel", "Barro", "","","", "", "",2,1));

        peladaAdapter = new PeladaAdapter(peladaList);
        recyclerView.setAdapter(peladaAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }
}

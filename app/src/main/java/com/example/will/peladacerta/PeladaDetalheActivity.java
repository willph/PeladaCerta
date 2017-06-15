package com.example.will.peladacerta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeladaDetalheActivity extends AppCompatActivity {

    private PeladaModel peladaModel;
    private Spinner spinner_time_1;
    private Spinner spinner_time_2;
    private TextView txtView_titulo;
    private TextView txtView_endereco;
    private TextView txtView_horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelada_detalhe);
        peladaModel = (PeladaModel) getIntent().getSerializableExtra("peladaModel");

        spinner_time_1 = (Spinner) findViewById(R.id.spinner_time_1);
        spinner_time_2 = (Spinner) findViewById(R.id.spinner_time_2);
        txtView_titulo = (TextView) findViewById(R.id.pelada_detalhes_textView_titulo);
        txtView_endereco = (TextView) findViewById(R.id.pelada_detalhes_textView_endereco);
        txtView_horario = (TextView) findViewById(R.id.pelada_detalhes_txtView_horario);

        txtView_titulo.setText(peladaModel.getTitle());
        txtView_endereco.setText(peladaModel.getAddress_full());
        txtView_horario.setText(peladaModel.getBegin());

        spinner_time_1.setAdapter(new SpinnerCustomAdapter(this));
        spinner_time_2.setAdapter(new SpinnerCustomAdapter(this));
    }
}

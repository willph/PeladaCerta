package com.example.will.peladacerta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.will.peladacerta.models.PeladaModel;
import com.example.will.peladacerta.models.User;
import com.example.will.peladacerta.util.IASyncFetchListener;
import com.example.will.peladacerta.util.Network;

import org.json.JSONArray;
import org.json.JSONObject;

public class PeladaDetalheActivity extends AppCompatActivity {

    private PeladaModel peladaModel;
    private TextView textView_time_1;
    private TextView textView_time_2;

    private Spinner spinner_time_1;
    private Spinner spinner_time_2;
    private TextView txtView_titulo;
    private TextView txtView_endereco;
    private TextView txtView_horario;
    private ImageButton imageButton_time_1;
    private ImageButton imageButton_time_2;
    private User loggerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loggerUser = new User();
        if (getIntent().getSerializableExtra("loggerUser") != null){
            loggerUser = (User) getIntent().getSerializableExtra("loggerUser");
        }


            setContentView(R.layout.activity_pelada_detalhe);
            peladaModel = (PeladaModel) getIntent().getSerializableExtra("peladaModel");

            textView_time_1 = (TextView) findViewById(R.id.textView_time_1);
            textView_time_2 = (TextView) findViewById(R.id.textView_time_2);

            spinner_time_1 = (Spinner) findViewById(R.id.spinner_time_1);
            spinner_time_2 = (Spinner) findViewById(R.id.spinner_time_2);
            txtView_titulo = (TextView) findViewById(R.id.pelada_detalhes_textView_titulo);
            txtView_endereco = (TextView) findViewById(R.id.pelada_detalhes_textView_endereco);
            txtView_horario = (TextView) findViewById(R.id.pelada_detalhes_txtView_horario);

            imageButton_time_1 = (ImageButton)findViewById(R.id.ImageButton_time_1);
            imageButton_time_2 = (ImageButton)findViewById(R.id.ImageButton_time_2);

            txtView_titulo.setText(peladaModel.getTitle());
            txtView_endereco.setText(peladaModel.getAddressFull());
            txtView_horario.setText(peladaModel.getBegin());
            textView_time_1.setText(peladaModel.getHostTeam().getTeam_name().toString());
            textView_time_2.setText(peladaModel.getGuestTeam().getTeam_name().toString());

            spinner_time_1.setAdapter(new SpinnerCustomAdapter(this, peladaModel.getHostTeam()));
            spinner_time_2.setAdapter(new SpinnerCustomAdapter(this, peladaModel.getGuestTeam()));

            imageButton_time_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Network network = new Network(PeladaDetalheActivity.this, "");
                    network.joinTeam(loggerUser, peladaModel.getHostTeam());
                    network.setListener(new IASyncFetchListener() {
                        @Override
                        public void onComplete(JSONObject item) {

                        }

                        @Override
                        public void onComplete(JSONArray jsonArray) {

                        }

                        @Override
                        public void onError(Throwable error) {

                        }

                        @Override
                        public void onError(Throwable error, JSONObject errorResponse) {

                        }
                    });




//                    spinner_time_1.setAdapter(new SpinnerCustomAdapter(PeladaDetalheActivity.this, peladaModel.getHostTeam()));
                }
            });

            imageButton_time_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Network network = new Network(PeladaDetalheActivity.this, "");
                    network.joinTeam(loggerUser, peladaModel.getGuestTeam());
//                    spinner_time_2.setAdapter(new SpinnerCustomAdapter(PeladaDetalheActivity.this, peladaModel.getGuestTeam()));
                }
            });

    }
}

package com.example.will.peladacerta;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.will.peladacerta.models.PeladaModel;
import com.example.will.peladacerta.models.SoccerTeam;
import com.example.will.peladacerta.models.User;
import com.example.will.peladacerta.util.IASyncFetchListener;
import com.example.will.peladacerta.util.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PeladaDetalheActivity extends AppCompatActivity {

    private PeladaModel peladaModel;
    private TextView textView_time_1;
    private TextView textView_time_2;

    private SpinnerCustomAdapter spinner_adapter_1;
    private SpinnerCustomAdapter spinner_adapter_2;

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

            spinner_adapter_1 = new SpinnerCustomAdapter(this, peladaModel.getHostTeam());
            spinner_adapter_2 = new SpinnerCustomAdapter(this, peladaModel.getGuestTeam());

            spinner_time_1.setAdapter(spinner_adapter_1);
            spinner_time_2.setAdapter(spinner_adapter_2);

            imageButton_time_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Network network = new Network(PeladaDetalheActivity.this, "");
                    network.joinTeam(loggerUser, peladaModel.getHostTeam());
                    network.setListener(new IASyncFetchListener() {
                        @Override
                        public void onComplete(JSONObject item) {
                            updatePelada(PeladaDetalheActivity.this, peladaModel.getId());
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
                    Network network = new Network(PeladaDetalheActivity.this, "Aguarde...");
                    network.joinTeam(loggerUser, peladaModel.getGuestTeam());
                    network.setListener(new IASyncFetchListener() {
                        @Override
                        public void onComplete(JSONObject item) {
                            updatePelada(PeladaDetalheActivity.this, peladaModel.getId());
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
//                    spinner_time_2.setAdapter(new SpinnerCustomAdapter(PeladaDetalheActivity.this, peladaModel.getGuestTeam()));
                }
            });

    }

    public void updatePelada(Context context, int id){
        Network network = new Network(context, "Aguarde...");
        network.atualizarPelada(id);
        network.setListener(new IASyncFetchListener() {
            @Override
            public void onComplete(JSONObject item) {

                SoccerTeam hostTeam = new SoccerTeam();
                SoccerTeam guestTeam = new SoccerTeam();
                JSONObject hostJSON;
                JSONObject guestJSON;
                JSONArray jsonArrayUsers;
                List<User> listaUsersHost;
                List<User> listaUsersGuest;
                User user;

                    try {


                        PeladaModel peladaModel = new PeladaModel(item.getInt("id"), item.getString("title"),
                                item.getString("begin"), item.getString("address_full"),
                                item.getDouble("lat"), item.getDouble("lng"), "http://cyberdados.com/wp-content/images/2014/07/2iitkhx.jpg");

                        hostJSON = item.getJSONObject("host");
                        hostTeam.setId(hostJSON.getInt("id"));
                        hostTeam.setTeamName(hostJSON.getString("team_name"));
                        listaUsersHost = new ArrayList<>();
                        jsonArrayUsers = hostJSON.getJSONArray("users");

                        for (int j = 0; j < jsonArrayUsers.length(); j++) {
                            JSONObject userJson = jsonArrayUsers.getJSONObject(j);
                            user = new User();
                            user.setId(userJson.getInt("id"));
                            user.setNome(userJson.getString("nome"));
                            user.setPosition(userJson.getString("position"));
                            user.setBirthdate(userJson.getString("birthdate"));
                            user.setCpf(userJson.getString("cpf"));
                            user.setActive(userJson.getString("active"));
                            user.setCell_phone(userJson.getString("cell_phone"));
                            user.setHome_phone(userJson.getString("home_phone"));
                            user.setDescricao(userJson.getString("descricao"));

                            listaUsersHost.add(user);
                        }

                        guestJSON = item.getJSONObject("guest");
                        guestTeam.setId(guestJSON.getInt("id"));
                        guestTeam.setTeamName(guestJSON.getString("team_name"));
                        listaUsersGuest = new ArrayList<>();
                        jsonArrayUsers = guestJSON.getJSONArray("users");

                        for (int k = 0; k < jsonArrayUsers.length(); k++) {
                            JSONObject userJson = jsonArrayUsers.getJSONObject(k);
                            user = new User();
                            user.setId(userJson.getInt("id"));
                            user.setNome(userJson.getString("nome"));
                            user.setPosition(userJson.getString("position"));
                            user.setBirthdate(userJson.getString("birthdate"));
                            user.setCpf(userJson.getString("cpf"));
                            user.setActive(userJson.getString("active"));
                            user.setCell_phone(userJson.getString("cell_phone"));
                            user.setHome_phone(userJson.getString("home_phone"));
                            user.setDescricao(userJson.getString("descricao"));

                            listaUsersGuest.add(user);
                        }


                        hostTeam.setListaJogadores(listaUsersHost);
                        guestTeam.setListaJogadores(listaUsersGuest);

                        peladaModel.setHostTeam(hostTeam);
                        peladaModel.setGuestTeam(guestTeam);

                        spinner_adapter_1 = new SpinnerCustomAdapter(PeladaDetalheActivity.this, peladaModel.getHostTeam());
                        spinner_adapter_2 = new SpinnerCustomAdapter(PeladaDetalheActivity.this, peladaModel.getGuestTeam());

                        spinner_adapter_1.notifyDataSetChanged();
                        spinner_adapter_2.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



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
    }
}

package com.example.will.peladacerta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.will.peladacerta.models.User;
import com.example.will.peladacerta.util.IASyncFetchListener;
import com.example.will.peladacerta.util.Network;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtSenha;
    private Button buttonEntrar;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        txtEmail = (TextView) findViewById(R.id.editTextEmail);
        txtSenha = (TextView) findViewById(R.id.editTextSenha);
        buttonEntrar = (Button) findViewById(R.id.buttonLogin);
        buttonCadastrar = (Button) findViewById(R.id.buttonSignIn);

        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        List<Cookie> cookies = myCookieStore.getCookies();
        for (Cookie c : cookies) {
            Log.i("lala", c.getName());
            if (c.getName().equals("_pelada_certa-api_session")) {
//                Intent intent = new Intent(LoginActivity.this, ListaPeladaCerta.class);
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }


        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Network network = new Network(LoginActivity.this, "Carregando");
                Intent intent = new Intent(LoginActivity.this, ListaPeladaCerta.class);
                network.login(txtEmail.getText().toString(), txtSenha.getText().toString());
                network.setListener(new IASyncFetchListener() {
                    @Override
                    public void onComplete(JSONObject item) {
//                        Intent intent = new Intent(LoginActivity.this, ListaPeladaCerta.class);

//                        User user = new User();
                        try {
                            User user = new User();
                            user.setId(item.getInt("id"));
                            user.setNome(item.getString("nome"));
                            user.setPosition(item.getString("position"));
                            user.setBirthdate(item.getString("birthdate"));
                            user.setCpf(item.getString("cpf"));
                            user.setActive(item.getString("active"));
                            user.setCellPhone(item.getString("cell_phone"));
                            user.setHomePhone(item.getString("home_phone"));
                            user.setDescricao(item.getString("descricao"));

                            SharedPreferences settings = getSharedPreferences("usuario_logado", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("usuario_id", String.valueOf(user.getId()));
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                            intent.putExtra("loggerUser", user);
                            startActivity(intent);
                            finish();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
//                        intent.putExtra("loggerUser", user);
//                        startActivity(intent);


                    }

                    @Override
                    public void onComplete(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Throwable error) {}

                    @Override
                    public void onError(Throwable error, JSONObject errorResponse) {}
                });
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}

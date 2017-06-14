package com.example.will.peladacerta;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.will.peladacerta.util.IASyncFetchListener;
import com.example.will.peladacerta.util.Network;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtSenha;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtEmail = (TextView) findViewById(R.id.editTextEmail);
        txtSenha = (TextView) findViewById(R.id.editTextSenha);
        btnEntrar = (Button) findViewById(R.id.buttonLogin);

        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        List<Cookie> cookies = myCookieStore.getCookies();
        for (Cookie c : cookies) {
            Log.i("lala", c.getName());
            if (c.getName().equals("_pelada_certa-api_session")) {
                Intent intent = new Intent(LoginActivity.this, ListaPeladaCerta.class);
                startActivity(intent);
                finish();
            }
        }


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Network network = new Network(LoginActivity.this, "Carregando");
                Intent intent = new Intent(LoginActivity.this, ListaPeladaCerta.class);
                network.login(txtEmail.getText().toString(), txtSenha.getText().toString());
                network.setListener(new IASyncFetchListener() {
                    @Override
                    public void onComplete(JSONObject item) {
                        Intent intent = new Intent(LoginActivity.this, ListaPeladaCerta.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onComplete(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Throwable error) {}

                    @Override
                    public void onError(Throwable error, JSONObject errorResponse) {}
                });



//                if (txtEmail.getText().toString().equals("admin") == true &&
//                        txtSenha.getText().toString().equals("admin") == true){
//
//                    Toast.makeText(getApplicationContext(), "Redirecionando...", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//
//                }else{
//                   /*//Mostra uma mensagem na tela com um botão
//
//                   AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
//                    alertDialog.setTitle("Alerta");
//                    alertDialog.setMessage("LOGIN OU SENHA INVALIDA");
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    alertDialog.show();*/
//                   //Apenas mostra uma mensagem que some sozinho.
//                    Toast.makeText(LoginActivity.this, "Erro.\nLogin ou senha invalido...", Toast.LENGTH_SHORT).show();
//
//                }

            }
        });
    }


}

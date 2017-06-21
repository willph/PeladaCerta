package com.example.will.peladacerta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.will.peladacerta.models.User;
import com.example.will.peladacerta.util.IASyncFetchListener;
import com.example.will.peladacerta.util.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    private Network network;

    private User user;

    private EditText signin_activity_editText_Nome;
    private EditText signin_activity_editText_Email;
    private EditText signin_activity_editText_Password;
    private EditText signin_activity_editText_Password_confirmation;
    private EditText signin_activity_editText_Cell_phone;

    private Button signin_activity_button_cadastrar;

    private Button signin_activity_button_cancelar;

    private Spinner positionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        network = new Network(this, "Cadastrando");

        positionSpinner = (Spinner) findViewById(R.id.signin_activity_spinner_positions);

        ArrayAdapter<String> listaPosition = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listaPosition.addAll("", "Atacante", "Goleiro", "Zagueiro", "Lateral Esquerdo", "Lateral Direito");
        positionSpinner.setAdapter(listaPosition);

        signin_activity_editText_Nome = (EditText) findViewById(R.id.signin_activity_editText_Nome);
        signin_activity_editText_Email = (EditText) findViewById(R.id.signin_activity_editText_Email);
        signin_activity_editText_Password = (EditText) findViewById(R.id.signin_activity_editText_Password);
        signin_activity_editText_Password_confirmation = (EditText) findViewById(R.id.signin_activity_editText_Password_confirmation);
        signin_activity_editText_Cell_phone = (EditText) findViewById(R.id.signin_activity_editText_Cell_phone);

        signin_activity_button_cadastrar = (Button) findViewById(R.id.signin_activity_button_cadastrar);
        signin_activity_button_cancelar = (Button) findViewById(R.id.signin_activity_button_cancelar);



        signin_activity_button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.setNome(signin_activity_editText_Nome.getText().toString());
                user.setEmail(signin_activity_editText_Email.getText().toString());
                user.setPassword(signin_activity_editText_Password.getText().toString());
                user.setPasswordConfirmation(signin_activity_editText_Password_confirmation.getText().toString());
                user.setCellPhone(signin_activity_editText_Cell_phone.getText().toString());
                user.setPosition(positionSpinner.getSelectedItem().toString());
                network.cadastrar(user);
                network.setListener(new IASyncFetchListener() {

                    @Override
                    public void onComplete(JSONObject item) {

                        loginAfertSignIn(item);
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
        });

        signin_activity_button_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void loginAfertSignIn(JSONObject item){
        Network network = new Network(SignInActivity.this, "Carregando");

        try {
            network.login(item.getString("email"), signin_activity_editText_Password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        network.setListener(new IASyncFetchListener() {
            @Override
            public void onComplete(JSONObject item) {
                try {
                    SharedPreferences settings = getSharedPreferences("usuario_logado", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("usuario_id", String.valueOf(item.getInt("id")));
                    editor.commit();

                    Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
                    intent.putExtra("loggedUser", user);
                    startActivity(intent);
                    finish();
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

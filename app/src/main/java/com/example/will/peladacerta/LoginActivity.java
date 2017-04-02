package com.example.will.peladacerta;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView txtEmail = (TextView) findViewById(R.id.editTextEmail);
        final TextView txtSenha = (TextView) findViewById(R.id.editTextSenha);
        Button btnEntrar = (Button) findViewById(R.id.buttonLogin);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(c, LogadoActivity.class);
                if (txtEmail.getText().toString().equals("admin") == true &&
                        txtSenha.getText().toString().equals("admin") == true){

                    Toast.makeText(getApplicationContext(), "Redirecionando...", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }else{
                   /*//Mostra uma mensagem na tela com um botão

                   AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setTitle("Alerta");
                    alertDialog.setMessage("LOGIN OU SENHA INVALIDA");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();*/
                   //Apenas mostra uma mensagem que some sozinho.
                    Toast.makeText(getApplicationContext(), "Erro.\nLogin ou senha invalido...", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}

package com.example.master.appreservaquadramaterialesportivo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Tiago Avellar on 17/06/2017.
 */

public class Login extends AppCompatActivity {


    private EditText login1,senha1;
    private String login,senha,testasenha;
    BancoDados db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);


        login1 = (EditText) findViewById(R.id.editText4);
        senha1 = (EditText) findViewById(R.id.editText5);
        this.db = new BancoDados(this);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, MainActivity.class);
                startActivity(it);

            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login.length() != 0 && senha.length() != 0
                login = login1.getText().toString();
                senha = senha1.getText().toString();

                testasenha = db.VerificaSenha(login);

                if (senha.equals(testasenha) ) {

                    Intent it = new Intent(Login.this, DepoisLogin.class);
                    startActivity(it);
                } else {
                    Toast.makeText(Login.this, "Dados Incorretos ", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(Login.this, Login.class);
                    startActivity(it);
                }
            }

        });

    }
}


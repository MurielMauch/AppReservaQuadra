package com.example.master.appreservaquadramaterialesportivo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Tiago Avellar on 17/06/2017.
 */

public class DepoisLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depois_login);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button14 = (Button) findViewById(R.id.button14);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(DepoisLogin.this, Reservar.class);
                startActivity(it);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(DepoisLogin.this, PesquisaReserva.class);
                startActivity(it);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(DepoisLogin.this, MainActivity.class);
                startActivity(it);

            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(DepoisLogin.this, CancelaReserva.class);
                startActivity(it);

            }
        });


    }
}
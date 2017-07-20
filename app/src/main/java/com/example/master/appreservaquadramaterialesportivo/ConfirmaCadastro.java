package com.example.master.appreservaquadramaterialesportivo;

/**
 * Created by Tiago Avellar on 17/06/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ConfirmaCadastro extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_cadastro);

        Button button12 = (Button)findViewById(R.id.button12);

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setContentView(R.layout.activity_depois_login);
                Intent it = new Intent(ConfirmaCadastro.this, MainActivity.class);
                startActivity(it);

            }
        });



    }
}

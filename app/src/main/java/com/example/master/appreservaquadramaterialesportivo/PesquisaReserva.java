package com.example.master.appreservaquadramaterialesportivo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tiago Avellar on 17/06/2017.
 */

public class PesquisaReserva extends AppCompatActivity {

    BancoDados db;
    private EditText txt_pesquisa;
    Integer codbancoint;
    String codbancostring,imprime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_reserva);

        Button button7 = (Button) findViewById(R.id.button7);
        Button button13 = (Button) findViewById(R.id.button13);
        Button button21 = (Button) findViewById(R.id.button21);
        txt_pesquisa = (EditText) findViewById(R.id.txt_pesquisa);

        this.db = new BancoDados(this);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codbancostring = txt_pesquisa.getText().toString();
              //imprime = db.BuscaReserva(new Quadra(codbancoint));
                imprime= db.BuscaReserva(codbancostring);
              //  Toast.makeText(PesquisaReserva.this, codbancostring , Toast.LENGTH_LONG).show();
               //Toast.makeText(PesquisaReserva.this, imprime , Toast.LENGTH_LONG).show();
                if(imprime.equals("sim"))
                    Toast.makeText(PesquisaReserva.this,"A quadra esta reservada", Toast.LENGTH_LONG).show();
                else{ if(imprime.equals("nao")){
                    Toast.makeText(PesquisaReserva.this, "A sua reserva esta na fila de espera", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(PesquisaReserva.this, "O seu codigo não existe", Toast.LENGTH_LONG).show();
                     }
                }


                    Intent it = new Intent(PesquisaReserva.this,PesquisaReserva.class);
                    startActivity(it);
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(PesquisaReserva.this, DepoisLogin.class);
                startActivity(it);

            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(PesquisaReserva.this, Clima.class);
                startActivity(it);

            }
        });


    }
}

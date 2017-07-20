package com.example.master.appreservaquadramaterialesportivo;

/**
 * Created by Tiago Avellar on 26/06/2017.
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.usage.UsageEvents;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.*;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.Spinner;
import android.view.Menu;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import static android.text.format.DateFormat.getDateFormat;


public class CancelaReserva extends AppCompatActivity  {


    BancoDados db;//Banco
    private EditText codigocancela;
    private String codigocan,imprime,busca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancela_reserva);


        Button button15 = (Button) findViewById(R.id.button15);
        Button button20 = (Button) findViewById(R.id.button20);

        codigocancela = (EditText) findViewById(R.id.codigocancela);// pega o codigo digitado
        this.db = new BancoDados(this);



        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigocan = codigocancela.getText().toString();
               // Toast.makeText(CancelaReserva.this, codigocan, Toast.LENGTH_LONG).show();
                busca=db.BuscaReserva(codigocan);
                if(busca.equals("nao")) {
                    imprime = db.CancelaReserva(codigocan);
                    Toast.makeText(CancelaReserva.this, imprime, Toast.LENGTH_LONG).show();
                    Intent it = new Intent(CancelaReserva.this, DepoisLogin.class);
                    startActivity(it);
                }
                else {
                    if (busca.equals("sim")) {
                        imprime = db.CancelaReservaAtualizaFila(codigocan);
                        Toast.makeText(CancelaReserva.this, imprime, Toast.LENGTH_LONG).show();
                        Intent it = new Intent(CancelaReserva.this, DepoisLogin.class);
                        startActivity(it);
                    }else {
                        Toast.makeText(CancelaReserva.this, busca, Toast.LENGTH_LONG).show();
                        Intent it = new Intent(CancelaReserva.this, CancelaReserva.class);
                        startActivity(it);
                    }
                }
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(CancelaReserva.this, DepoisLogin.class);
                startActivity(it);

            }
        });
    }
}

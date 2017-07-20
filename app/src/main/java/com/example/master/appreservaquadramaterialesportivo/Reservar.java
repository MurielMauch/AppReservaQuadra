package com.example.master.appreservaquadramaterialesportivo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.usage.UsageEvents;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
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

public class Reservar extends AppCompatActivity {

    //Quadra
    private Spinner spinner2;
    TextView txvOpcaoSelecionada;
    private ArrayAdapter<String> adpTipoQuadra;

    //Data e Hora
    Calendar dateTime = Calendar.getInstance();
    private TextView data2;

    //Banco
   BancoDados db;
    private String data, hora, quadra,codigo;
    int ano1, dia1, mes1, hora1,contador,cod1,contadorfila;
    private static int contador_geral=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);


        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        //Data
        Button btn_data = (Button) findViewById(R.id.btn_data);
        Button btn_hora = (Button) findViewById(R.id.btn_hora);

        //Banco
        this.db = new BancoDados(this);

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contador = db.ContadorReserva(new Quadra(quadra,ano1,mes1,dia1,hora1));
                new Quadra(contador);
                contadorfila = db.ContadorFilaReserva(new Quadra(quadra,ano1,mes1,dia1,hora1,contador));
                new Quadra(contador,contadorfila);
                //contador = db.ContadorReserva(quadra,ano1,mes1,dia1,hora1);
                if(contadorfila == -1){
                    // db.addQuadra(quadra,ano1,mes1,dia1,hora1,contador,contadorfila);
                   db.addQuadra(new Quadra(quadra,ano1,mes1,dia1,hora1,contador,contadorfila));
                   // Toast.makeText(Reservar.this, "A quadra foi reservada com sucesso ", Toast.LENGTH_LONG).show();
                   // codigo = db.VerificaCodigo(quadra,ano1,mes1,dia1,hora1,contador);
                    //codigo = db.VerificaCodigo(new Quadra(quadra,ano1,mes1,dia1,hora1,contador));
                    Toast.makeText(Reservar.this, "Seu codigo é : "+ ++contador , Toast.LENGTH_LONG).show();
                    Intent it = new Intent(Reservar.this, ConfirmaReserva.class);
                    startActivity(it);

                }else{
                    db.addQuadra(new Quadra(quadra,ano1,mes1,dia1,hora1,contador,contadorfila));
                   // db.addQuadra(quadra,ano1,mes1,dia1,hora1,contador,contadorfila);
                        Toast.makeText(Reservar.this, "A quadra ja estava reservada, sua solicitação foi para a fila de espera ", Toast.LENGTH_LONG).show();
                      //  codigo = db.VerificaCodigo(quadra,ano1,mes1,dia1,hora1,contador);
                      // codigo = db.VerificaCodigo(new Quadra(quadra,ano1,mes1,dia1,hora1,contador));
                        Toast.makeText(Reservar.this, "Seu codigo é : "+ ++contador , Toast.LENGTH_LONG).show();
                        Intent it = new Intent(Reservar.this, Reservar.class);
                        startActivity(it);
                    }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Reservar.this, DepoisLogin.class);
                startActivity(it);

            }
        });

        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 updateData();

            }
        });

        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   updateTime();
            }
        });

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        adpTipoQuadra = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);//
        spinner2.setAdapter(adpTipoQuadra);
        //adicionando elemestos no spinner
        adpTipoQuadra.add("Quadra 1");
        adpTipoQuadra.add("Quadra 2");
        adpTipoQuadra.add("Quadra 3");
        adpTipoQuadra.add("Quadra 4");

        //pegando item selecionado do spinner
        txvOpcaoSelecionada = (TextView) findViewById(R.id.txt_opcaoselecionada);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txvOpcaoSelecionada.setText(adpTipoQuadra.getItem(position));
                quadra = adpTipoQuadra.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void updateData() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

   DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int ano, int mes, int dia) {
            dateTime.set(Calendar.YEAR, ano);
            ano1 = ano;
            dateTime.set(Calendar.MONTH, mes);
            mes1 = mes;
            dateTime.set(Calendar.DAY_OF_MONTH, dia);
            dia1 = dia;
            //data2.setText(dia + "/" + mes + "/" + ano);
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hora, int minuto) {
            dateTime.set(Calendar.HOUR_OF_DAY, hora);
            hora1 = hora;
            dateTime.set(Calendar.MINUTE, 0);
        }
    };
}



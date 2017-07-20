package com.example.master.appreservaquadramaterialesportivo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import data.Channel;
import data.Item;

/**
 * Created by Tiago Avellar on 01/07/2017.
 */

public class Clima extends AppCompatActivity implements WeatherServiceCallback {
    private ImageView weatherIconImageView;
    private TextView temperatura;
    private TextView condicao;
    private TextView localizacao;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima);


        Button button22 = (Button) findViewById(R.id.button22);

        weatherIconImageView = (ImageView) findViewById(R.id.imageView);
        temperatura = (TextView) findViewById(R.id.temperatura);
        condicao = (TextView) findViewById(R.id.condicaoclima);
        localizacao = (TextView) findViewById(R.id.localizacao);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Sao Carlos, Brasil");

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Clima.this, PesquisaReserva.class);
                startActivity(it);

            }
        });

    }

    public void serviceSucess(Channel channel){
        dialog.hide();

        Item item = channel.getItem();
       // int resourceId = getResources().getIdentifier("drawbale/na_logo" + channel.getItem().getCondicao().getCodigo(), null, getPackageName());
         int resourceId = getResources().getIdentifier("drawbale/icon_" + channel.getItem().getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
       Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatura.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        condicao.setText(item.getCondition().getDescription());
        localizacao.setText(service.getLocation());
    }
    public void serviceFailure(Exception exception){
        dialog.hide();
        Toast.makeText(this, exception.getMessage(),Toast.LENGTH_LONG).show();
    }


}
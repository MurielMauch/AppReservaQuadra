package com.example.master.appreservaquadramaterialesportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tiago Avellar on 17/06/2017.
 */

public class ConfirmaReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_reserva);

        Button button19 = (Button) findViewById(R.id.button19);


        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfirmaReserva.this, DepoisLogin.class);
                startActivity(it);

            }
        });
    }
}
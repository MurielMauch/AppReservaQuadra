package com.example.master.appreservaquadramaterialesportivo;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;



public class MenuAjuda extends AppCompatActivity  {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ajuda);

        Button button16 = (Button) findViewById(R.id.button16);


        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuAjuda.this, MainActivity.class);
                startActivity(it);

            }
        });

    }
}

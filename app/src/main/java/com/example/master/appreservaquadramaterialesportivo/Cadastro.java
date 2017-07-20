package com.example.master.appreservaquadramaterialesportivo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText.*;
import android.widget.Spinner;
import android.widget.*;

import java.util.Properties;

/**
 * Created by Tiago Avellar on 15/06/2017.
 */

public class Cadastro extends AppCompatActivity{

    //variaveis dos campos em branco
    private EditText txt_identificao;
    private EditText txt_email;
    private EditText txt_nome;
    private EditText txt_senha,txt_confsenha;
    private String Nome,Email,Senha,Identificao,confsenha;

    BancoDados db;
    private Button button18,button6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txt_nome = (EditText) findViewById(R.id.txt_nome);
        txt_identificao = (EditText) findViewById(R.id.txt_identificao);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_senha = (EditText) findViewById(R.id.txt_senha);
        txt_confsenha = (EditText) findViewById(R.id.txt_confsenha);

        Button button18 = (Button) findViewById(R.id.button18);
        Button button6 = (Button) findViewById(R.id.button6);
        this.db = new BancoDados(this);

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Nome = txt_nome.getText().toString();
                Identificao = txt_identificao.getText().toString();
                Email = txt_email.getText().toString();
                Senha = txt_senha.getText().toString();
                confsenha = txt_confsenha.getText().toString();

                if (txt_nome.length() != 0 && txt_identificao.length() != 0 && txt_email.length() != 0 && txt_senha.length() != 0 && txt_confsenha.length() != 0 && (!txt_confsenha.equals(txt_senha))) {
                    db.CadastraCliente(new Cliente(Nome,Identificao,Email,Senha));
                    // db.CadastraCliente(Identificao, Nome, Senha, Email);
                    //enviarEmail();
                    Toast.makeText(Cadastro.this, "Cadastro Salvo", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(Cadastro.this, ConfirmaCadastro.class);
                    startActivity(it);

                } else {
                    Toast.makeText(Cadastro.this, "Falta Dados para o Cadastro ", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(Cadastro.this, Cadastro.class);
                    startActivity(it);
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setContentView(R.layout.activity_depois_login);
                Intent it = new Intent(Cadastro.this, MainActivity.class);
                startActivity(it);

            }
        });
    }


    //Email
    /*
        public boolean isOnline() {
            try {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                return netInfo != null && netInfo.isConnectedOrConnecting();
            }
            catch(Exception ex){
                Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! (" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        private void enviarEmail(){

            if(isOnline()) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Mail m = new Mail("reservaquadraufscar@gmail.com", "reserva1234");

                        String[] toArr = {Email};
                        m.setTo(toArr);

                        m.setFrom("reservaquadraufscar@gmail.com");
                        m.setSubject("Cadastro feito com sucesso");
                        m.setBody(Nome + " foi cadastrado com sucesso!" + " Seu login: " + Identificao + " Senha: " + Senha );

                        try {
                            //m.addAttachment("pathDoAnexo");//anexo opcional
                            m.send();
                        }
                        catch(RuntimeException rex){ }//erro ignorado
                        catch(Exception e) {
                            //tratar algum outro erro aqui
                        }

                        System.exit(0);
                    }
                }).start();
            }
            else {
                Toast.makeText(getApplicationContext(), "Não estava online para enviar e-mail!", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        }*/

/*
        spinner1= (Spinner) findViewById(R.id.spinner1);
        adpTipoProf = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);//
        spinner1.setAdapter(adpTipoProf);

        adpTipoProf.add("Estudante");
        adpTipoProf.add("Docente");

       */

/*
    //Teste do Crud
        //adicionando
        db.addCliente(new Cliente("Tiago","551910", "tiagoalpaca@gmail.com"));
        db.addCliente(new Cliente("Alessandra", "552038", "alesouza.camargo@gmail.com"));
        db.addCliente(new Cliente("Muriel", "551111", "murielmauch@gmail.com"));
        db.addCliente(new Cliente("Benzelara", "551111", "benzelara@gmail.com"));

        //apagando
        Cliente cliente = new Cliente();
        cliente.setCodigo(3);
        db.apagarCliente(cliente);

        //selecionando
        Cliente cliente = new Cliente();
        cliente.setCodigo(4);
        db.selecionarCliente(cliente);

        Log.d("Cliente Selecionado", "Codigo" + cliente.getCodigo() + " Nome" + cliente.getNome() + "Identificacao" + cliente.getIdentificao()
        + " Email" + cliente.getEmail());

        //Atualizando
        Cliente cliente = new Cliente();
        cliente.setCodigo(4);
        cliente.setNome("Alexandra Lara");
        cliente.setIdentificao("551817");
        cliente.setEmail("alexandrelara@gmail.com");

        public void listar


        Toast.makeText(Cadastro.this,"Salvo com Sucesso",Toast.LENGTH_LONG).show();
        Toast.makeText(Cadastro.this,"Apagado com Sucesso",Toast.LENGTH_LONG).show();
        Toast.makeText(Cadastro.this,"Selecionado com Sucesso",Toast.LENGTH_LONG).show();
        Toast.makeText(Cadastro.this,"Alterado com Sucesso",Toast.LENGTH_LONG).show();

*/

    }



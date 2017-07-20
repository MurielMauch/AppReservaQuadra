package com.example.master.appreservaquadramaterialesportivo;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tiago Avellar on 19/06/2017.
 */

public class Cliente extends AppCompatActivity {


    String nome,identificacao,email,senha;

    public Cliente(){}

    public Cliente(String nome1,String identificacao1,String email1,String senha){

        this.nome = nome1;
        this.identificacao = identificacao1;
        this.email = email1;
        this.senha=senha;
    }


    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha=senha;
    }
    public String getNome(){
        return nome;
    }
     public void  setNome(String nome){
       this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }
    public String getIdentificacao(){
        return identificacao;
    }

    public void setIdentificacaoo(String identificacao){
        this.identificacao=identificacao;
    }


}

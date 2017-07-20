package com.example.master.appreservaquadramaterialesportivo;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tiago Avellar on 24/06/2017.
 */
import java.util.Random;
public class Quadra extends AppCompatActivity {

    int hora,dia,mes,ano,contador,contadorfila;
    //int codigorandon;
    String quadra,reserva[];
   // Random gerador = new Random();

    public Quadra(){}

    public void setContadorfila(int contadorfila) {
        this.contadorfila = contadorfila;
    }

    public void setReserva(String[] reserva) {
        this.reserva = reserva;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public int getContadorfila() {
        return contadorfila;
    }

    public String[] getReserva() {
        return reserva;
    }

    public Quadra(String quadra, String reservar[], int hora, int dia, int mes, int ano, int contador, int contadorfila){
        this.quadra = quadra;
        this.reserva[0] = "reservado ";

        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.contador= contador;
        this.contadorfila = contadorfila;
    }

    public Quadra(String quadra,int hora,int dia,int mes,int ano,int contador,int contadorfila){
        this.quadra = quadra;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.contador= contador;
        this.contadorfila = contadorfila;
    }

    public Quadra(String quadra,int hora,int dia,int mes,int ano,int contador){
        this.quadra = quadra;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.contador= contador;
    }

    public Quadra(String quadra,int hora,int dia,int mes,int ano){
        this.quadra = quadra;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Quadra(int codigo){

        contador = codigo;
    }
    public Quadra(Integer codigo){

        contador = codigo;
    }
    public Quadra(int contador,int contadorfila){

        this.contador=contador;
        this.contadorfila=contadorfila;
    }

    public String getQuadra(){
        return quadra;
    }

    public void setQuadrao(String quadra){
        this.quadra=quadra;
    }

    public String getReserva(int cont){
        return reserva[cont];
    }

    public void setReserva(int contador,String opcao){
        this.reserva[contador] = "opcao";
    }

    public int getHora(){
        return hora;
    }

    public void setHora(int hora){
        this.hora = hora;
    }

    public int getDia(){
        return dia;
    }

    public void setDia(int dia){
        this.dia = dia;
    }

    public int getMes(){
        return mes;
    }

    public void setMes(int mes){
        this.mes = mes;
    }

    public int getAno(){
        return ano;
    }

    public void setAno(int ano){
        this.ano = ano;
    }


    public int getContador(){
        return contador;
    }

    public void setContador(int contador){
        this.contador = contador;
    }

}

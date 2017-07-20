package com.example.master.appreservaquadramaterialesportivo;

/**
 * Created by Tiago Avellar on 19/06/2017.
*/
 import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
 import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
 import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.*;
import android.content.*;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

 import static android.R.attr.id;


public class BancoDados extends SQLiteOpenHelper {

    //private static final int VERSAO_BANCO = 1 - 10;
    //private static final String BANCO_CLIENTE = "bd_reserva"-bd;
    private static final int VERSAO_BANCO = 14;
    private static final String BANCO_CLIENTE = "sd3";

    //DADOS DOS ALUNOS
    private static final String TABELA_CLIENTE = "td_cliente";
    private static final String COLUNA_CODIGO = "codigo";// identificação,tipo RA
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_SENHA = "senha";
    private static final String COLUNA_IDCliente = "idcliente";

    //INFORMAÇOES SOBRE QUADRA
    private static final String TABELA_QUADRA = "td_quadra";
    //private static final String COLUNA_IDQuadra = "idquadra";
    private static final String COLUNA_QUADRA = "quadra";
    private static final String COLUNA_CODIGORANDON = "codigorandon";
    private static final String COLUNA_ANO = "ano";
    private static final String COLUNA_MES = "mes";
    private static final String COLUNA_DIA = "dia";
    private static final String COLUNA_HORA = "hora";
    //private static final String COLUNA_DATA = "data";

    //Para gerar o controle das reservas, vendo como reservar
    private static final String COLUNA_RESERVA = "reserva"; // se a quadra for reservada recebe o sim
    private static final String COLUNA_CONTADOR = "contador"; //o contador é pra armazenar as reservas posteriores, como um vetor
    private static final String COLUNA_CONTADORFILA = "contadorfila"; //o contador fila é pra armazenar a fila de reserva.


    //criar banco de dados
    private static final String QUERY_COLUNAQUADRA5 = "CREATE TABLE " + TABELA_QUADRA + "(" + COLUNA_CODIGORANDON + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUNA_QUADRA + "  TEXT," + COLUNA_ANO + " INTEGER," + COLUNA_MES + " INTEGER," + COLUNA_DIA + " INTEGER," + COLUNA_HORA + " INTEGER," + COLUNA_RESERVA + " TEXT," + COLUNA_CONTADORFILA + " INTEGER," + COLUNA_CONTADOR + " INTEGER)";
    private static final String QUERY_COLUNACLIENTE5 = "CREATE TABLE " + TABELA_CLIENTE + "(" + COLUNA_IDCliente + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUNA_NOME + " TEXT," + COLUNA_CODIGO + " TEXT," + COLUNA_SENHA + " TEXT," + COLUNA_EMAIL + " TEXT)";

    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(QUERY_COLUNACLIENTE5);
        db.execSQL(QUERY_COLUNAQUADRA5);

        try {
            // db.execSQL(QUERY_COLUNACLIENTE5);
            //db.execSQL(QUERY_COLUNAQUADRA5);
        } catch (Exception error) {
            Log.e(BANCO_CLIENTE, "downloadDatabase Error: ", error);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS" + TABELA_QUADRA);
        db.execSQL("DROP IF TABLE EXISTS" + TABELA_CLIENTE);
        // db.execSQL("DROP IF TABLE EXISTS" + TABELA_QUADRA);
        onCreate(db);
    }

    //Cadastra o cliente no sistema usando a classe modelo
    public void CadastraCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_CODIGO, cliente.getIdentificacao());
        values.put(COLUNA_SENHA, cliente.getSenha());
        values.put(COLUNA_EMAIL, cliente.getEmail());

        db.insert(BancoDados.TABELA_CLIENTE, null, values);
        db.close();

    }

    //Cadastra o cliente no sistema pegando os atributos diretos
    public void CadastraCliente(String RA, String nome, String senha, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, nome);
        values.put(COLUNA_CODIGO, RA);
        values.put(COLUNA_SENHA, senha);
        values.put(COLUNA_EMAIL, email);

        db.insert(BancoDados.TABELA_CLIENTE, null, values);
        db.close();

    }

    //passeu RA e ele confere se a senha esta correta, usado no login e usando classe modelo
    public String VerificaSenha(Cliente cliente) {
        SQLiteDatabase db = this.getReadableDatabase();

        String VerSenha = "select codigo,senha from " + TABELA_CLIENTE;
        Cursor cursor = db.rawQuery(VerSenha, null);
        String username, passaword;
        passaword = "Nao encontrado";

        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);

                if (username.equals(cliente.getIdentificacao())) {
                    passaword = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return passaword;
    }

    //passeu RA e ele confere se a senha esta correta, usado no login
    public String VerificaSenha(String identificacao) {
        SQLiteDatabase db = this.getReadableDatabase();

        String VerSenha = "select codigo,senha from " + TABELA_CLIENTE;
        Cursor cursor = db.rawQuery(VerSenha, null);
        String username, passaword;
        passaword = "Nao encontrado";

        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);

                if (username.equals(identificacao)) {
                    passaword = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return passaword;
    }

    //Para verificar o codigo da quadra usando a classe modelo
    public String VerificaCodigo(Quadra quadra) {
        SQLiteDatabase db = this.getWritableDatabase();

        String VerCodigo = "select quadra, hora, dia, mes, ano, contador from " + TABELA_QUADRA;

        Cursor cursor = db.rawQuery(VerCodigo, null);
        int horabanco, diabanco, mesbanco, anobanco, contadorbanco, randon1;
        String randon = "este codigo não esta registado", quadrabanco;


        if (cursor.moveToFirst()) {
            do {

                quadrabanco = cursor.getString(0);
                horabanco = cursor.getInt(1);
                diabanco = cursor.getInt(2);
                mesbanco = cursor.getInt(3);
                anobanco = cursor.getInt(4);
                contadorbanco = cursor.getInt(5);

                if (quadrabanco.equals(quadra.getQuadra()) && (quadra.getAno() == anobanco) && (quadra.getMes() == mesbanco) && (quadra.getDia() == diabanco) && (quadra.getHora() == horabanco) && (quadra.getContador() == contadorbanco)) {
                    return randon = "Sua reserva na " + quadrabanco + " na hora" + horabanco + "no dia " + diabanco + "no mes " + mesbanco + "no ano" + anobanco + "esta confirmada";
                    // randon= + contadorbanco;
                }
            } while (cursor.moveToNext());

        }
        //return -1;
        return randon;
    }

    //Para verificar o codigo da quadra
    public String VerificaCodigo(String quadra, int ano, int mes, int dia, int hora, int cont) {
        SQLiteDatabase db = this.getWritableDatabase();

        String VerCodigo = "select codigorandon, quadra, hora, dia, mes, ano, contador from " + TABELA_QUADRA;

        Cursor cursor = db.rawQuery(VerCodigo, null);
        int horabanco, diabanco, mesbanco, anobanco, contadorbanco, randon1;
        String randon = "este codigo não esta registado", quadrabanco;


        if (cursor.moveToFirst()) {
            do {
                //randon1 = cursor.getInt(0);
                randon = cursor.getString(0);
                quadrabanco = cursor.getString(1);
                horabanco = cursor.getInt(2);
                diabanco = cursor.getInt(3);
                mesbanco = cursor.getInt(4);
                anobanco = cursor.getInt(5);
                contadorbanco = cursor.getInt(6);

                if (quadrabanco.equals(quadra) && (ano == anobanco) && (mes == mesbanco) && (dia == diabanco) && (hora == horabanco) && (cont == contadorbanco)) {
                    //  mensagem = "Sua reserva na " + quadrabanco + " na hora" + horabanco + "no dia " + diabanco + "no mes " + mesbanco + "no ano" + anobanco + "esta confirmada";
                    return randon;
                }
            } while (cursor.moveToNext());

        }
        //return -1;
        return randon;
    }

    //usado para registar uma reserva em uma quadra,usando classe modelo
    public void addQuadra(Quadra quadra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int cont, contfila;
        if (quadra.getContadorfila() == -1)//primeira inserção na data
        {
            cont = quadra.getContador();
            cont++;

            values.put(COLUNA_QUADRA, quadra.getQuadra());
            values.put(COLUNA_ANO, quadra.getAno());
            values.put(COLUNA_MES, quadra.getMes());
            values.put(COLUNA_DIA, quadra.getDia());
            values.put(COLUNA_HORA, quadra.getHora());
            values.put(COLUNA_RESERVA, "sim");
            values.put(COLUNA_CONTADOR, cont);
            values.put(COLUNA_CONTADORFILA, 0);

        } else {//armazena os valores com o contador incrementando para manter a fila eo reserva recebe nao
            cont = quadra.getContador();
            cont++;
            contfila = quadra.getContadorfila();
            contfila++;
            values.put(COLUNA_QUADRA, quadra.getQuadra());
            values.put(COLUNA_ANO, quadra.getAno());
            values.put(COLUNA_MES, quadra.getMes());
            values.put(COLUNA_DIA, quadra.getDia());
            values.put(COLUNA_HORA, quadra.getHora());
            values.put(COLUNA_RESERVA, "nao");
            values.put(COLUNA_CONTADOR, cont);
            values.put(COLUNA_CONTADORFILA, contfila);
        }

        db.insert(BancoDados.TABELA_QUADRA, null, values);
        db.close();
    }

    //usado para registar uma reserva em uma quadra
    public void addQuadra(String quadra, int ano, int mes, int dia, int hora, int cont, int contflia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (contflia == -1)//primeira inserção na data
        {
            cont = cont + 1;
            values.put(COLUNA_QUADRA, quadra);
            values.put(COLUNA_ANO, ano);
            values.put(COLUNA_MES, mes);
            values.put(COLUNA_DIA, dia);
            values.put(COLUNA_HORA, hora);
            values.put(COLUNA_RESERVA, "sim");
            values.put(COLUNA_CONTADOR, cont);
            values.put(COLUNA_CONTADORFILA, 0);
        } else {//armazena os valores com o contador incrementando para manter a fila eo reserva recebe nao
            cont = cont + 1;
            contflia = contflia + 1;
            values.put(COLUNA_QUADRA, quadra);
            values.put(COLUNA_ANO, ano);
            values.put(COLUNA_MES, mes);
            values.put(COLUNA_DIA, dia);
            values.put(COLUNA_HORA, hora);
            values.put(COLUNA_RESERVA, "nao");
            values.put(COLUNA_CONTADOR, cont);
            values.put(COLUNA_CONTADOR, contflia);
        }

        db.insert(BancoDados.TABELA_QUADRA, null, values);
        db.close();

        // String CodigoReserva = "SELECT  codigorandon FROM " + TABELA_QUADRA +  "WHERE " + COLUNA_QUADRA + COLUNA_ANO + COLUNA_MES + COLUNA_DIA + " = ?" + new String[]{quadra,String.valueOf(ano),String.valueOf(mes),String.valueOf(dia),String.valueOf(hora)};
        //retorna CodigoReserva;
    }

    //Cancelar uma reserva, deletando a coluna
    public String CancelaReserva(String cod) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mensagem="Reserva deletada com sucesso";

        db.delete(TABELA_QUADRA, COLUNA_CONTADOR + " = ?", new String[]{cod});
        db.close();
        return mensagem;
    }

    //Cancelar uma reserva, deletando a coluna,usando classe modelo
    public String CancelaReserva(Quadra quadra) {
        SQLiteDatabase db = this.getWritableDatabase();

        String VerCodigocan = "SELECT contador FROM " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerCodigocan, null);
        //delete from tablename where id='1' and name ='jack'
        //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
        int codigo = 0;
        Integer transforma;
        //transforma = Integer.parseInt(cod);
        String vai, mensagem = "codigo invalido";

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getInt(0);
                //vai = cursor.getString(0);
                //vai.equals(quadra.getContador())
                // if (vai.equals(String.valueOf(quadra.getContador()))) {//String.valueOf(cod)}
                if (codigo == quadra.getContador()) {
                    // db.delete(TABELA_QUADRA, COLUNA_CONTADOR + " = ?", new String[]{vai});
                    db.delete(TABELA_QUADRA, COLUNA_CONTADOR + " = ?", new String[]{String.valueOf(quadra.getContador())});
                    //db.delete(TABELA_QUADRA, COLUNA_CONTADOR + " = ?", new String[]{quadra.getCodigorandon()});
                    db.close();
                    return mensagem = "Quadra deletada com sucesso";
                }
            } while (cursor.moveToNext());
        }
        return mensagem;
    }

    //Retorna o contador  da classe com base nos dados da quadra,usando classe modelo
    public int ContadorFilaReserva(Quadra quadra) {

        SQLiteDatabase db = this.getReadableDatabase();
        String VerQuadra = "select quadra,ano,mes,dia,hora,contador,contadorfila from " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerQuadra, null);
        String quadra2, reservar2, mensagem = "não";
        int ano2, mes2, dia2, hora2, contador2, contfila;

        if (cursor.moveToFirst()) {
            do {
                quadra2 = cursor.getString(0);
                ano2 = cursor.getInt(1);
                mes2 = cursor.getInt(2);
                dia2 = cursor.getInt(3);
                hora2 = cursor.getInt(4);
                contador2 = cursor.getInt(5);
                contfila = cursor.getInt(6);

                if (quadra2.equals(quadra.getQuadra()) && (hora2 == quadra.getHora()) && (ano2 == quadra.getAno()) && (mes2 == quadra.getMes()) && (dia2 == quadra.getDia()) && (contador2 == quadra.getContador())) {
                    return contfila;
                }
            } while (cursor.moveToNext());
        }
        return -1;
    }

    public int ContadorReserva(Quadra quadra) {

        SQLiteDatabase db = this.getReadableDatabase();
        String VerQuadra = "select contador from " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerQuadra, null);
        int contador2 = -1;

        if (cursor.moveToFirst()) {
            do {
                contador2 = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return contador2;
    }

    //Retorna o contador  da classe com base nos dados da quadra
    public int ContadorReserva(String quadra, int ano, int mes, int dia, int hora) {

        SQLiteDatabase db = this.getReadableDatabase();
        String VerQuadra = "select quadra,ano,mes,dia,hora,contador from " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerQuadra, null);
        String quadra2, reservar2, mensagem = "não";
        int ano2, mes2, dia2, hora2, contador2, cont = -1;

        if (cursor.moveToFirst()) {
            do {
                quadra2 = cursor.getString(0);
                ano2 = cursor.getInt(1);
                mes2 = cursor.getInt(2);
                dia2 = cursor.getInt(3);
                hora2 = cursor.getInt(4);
                //contador2 = cursor.getInt(5);

                if (quadra2.equals(quadra) && (hora2 == hora) && (ano2 == ano) && (mes2 == mes) && (dia2 == dia)) {
                    cont++;
                }
            } while (cursor.moveToNext());
        }
        return cont;
    }

    //Busca uma reserva com base no codigorandon(id da quadra),usando classe modelo
    public String BuscaReserva(Quadra quadra) {

        SQLiteDatabase db = this.getReadableDatabase();
        String VerQuadra = " Select contador, reserva from " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerQuadra, null);
        String mensagem;
        int contador;
        mensagem = "Nao encontrado";


        if (cursor.moveToFirst()) {
            do {
                contador = cursor.getInt(0);
                if (contador == (quadra.getContador())) {
                    mensagem = cursor.getString(1);
                    return mensagem;
                }
            } while (cursor.moveToNext());
        }
        return mensagem;
    }

    //Busca uma reserva com base no codigorandon(id da quadra)
    public String BuscaReserva(String codigo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String VerQuadra = "select reserva, contador from " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerQuadra, null);
        String mensagem, codcursor2;
        mensagem = "Nao encontrado";

        if (cursor.moveToFirst()) {
            do {
                codcursor2 = cursor.getString(1);
               // if (codigo.equals(codcursor2)) {
                if(codcursor2.equals(codigo)){
                    mensagem = cursor.getString(0);
                    return mensagem;
                }
            } while (cursor.moveToNext());
        }
        return mensagem;
    }

    //Cancela uma reserva e atualiza a fila
    public String CancelaReservaAtualizaFila(String codigo) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        String Cancela = "Select quadra,ano,mes,dia,hora,reserva,contador from " + TABELA_QUADRA;
        Cursor cursorcancela = db1.rawQuery(Cancela, null);
        String mensagem = "Não foi encontrado codigo";
        String quadra1, reserva,cont1,FilaStatus ="não foi na funcao"; // para pegar os dados e passar pra atualizar a fila
        int ano, mes, dia, hora, contador, contadorfila;//

        if (cursorcancela.moveToFirst()) {
            do {
                quadra1 = cursorcancela.getString(0);
                ano = cursorcancela.getInt(1);
                mes = cursorcancela.getInt(2);
                dia = cursorcancela.getInt(3);
                hora = cursorcancela.getInt(4);
                reserva = cursorcancela.getString(5);
                cont1 = cursorcancela.getString(6);

                if (cont1.equals(codigo)) {
                      FilaStatus= atualizaFila(quadra1, ano, mes, dia, hora);
                        db1.delete(TABELA_QUADRA, COLUNA_CONTADOR + " = ?", new String[]{String.valueOf(cont1)});
                        db1.close();
                        mensagem = "Reserva deletada e "+ FilaStatus;
                        return mensagem;
                    }
            } while (cursorcancela.moveToNext());
        }
        return mensagem;
    }

    //atualza a fila da reserva, usado dentro do cancela reserva e anda fila
    public String atualizaFila(String quadra,int ano, int mes,int dia,int hora) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String VerReserva = " Select quadra,ano,mes,dia,hora,contadorfila,reserva,contador from " + TABELA_QUADRA;
        Cursor cursor = db.rawQuery(VerReserva, null);
        String quadra1, reserva, mensagem,contador;
        int ano1, mes1, dia1, hora1,contadorfila;

        if (cursor.moveToFirst()) {
            do {
                quadra1 = cursor.getString(0);
                ano1 = cursor.getInt(1);
                mes1 = cursor.getInt(2);
                dia1 = cursor.getInt(3);
                hora1 = cursor.getInt(4);
                contadorfila = cursor.getInt(5);
                reserva = cursor.getString(6);
                contador = cursor.getString(7);
                if ((quadra1.equals(quadra)) && (ano == ano1) && (mes == mes1) && (dia == dia1) && (hora == hora1) && (reserva.equals("nao"))) {
                    values.put(COLUNA_RESERVA,"sim");
                    db.update(TABELA_QUADRA, values, COLUNA_CONTADOR + " = ?", new String[]{String.valueOf(contador)});
                    mensagem = " A fila foi atualizada";
                    return mensagem;
                }
            } while (cursor.moveToNext());
        }
        mensagem = " Não tinha ninguem na fila";
        return mensagem;
    }
}

        //Crud abaixo
/*
        void addCliente(Cliente cliente){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUNA_NOME, cliente.getNome());
            values.put(COLUNA_CODIGO, cliente.getIdentificacao());
            values.put(COLUNA_EMAIL, cliente.getEmail());
            values.put(COLUNA_SENHA,cliente.getSenha());

            db.insert(TABELA_CLIENTE, null, values);
            db.close();

        }
    /*
        void apagarCliente (Cliente cliente){
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABELA_CLIENTE, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(cliente.getCodigo())});
            db.close();
        }
        /*
        Cliente selecionarCliente(int codigo){
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABELA_CLIENTE, new String[]{COLUNA_CODIGO, COLUNA_NOME, COLUNA_EMAIL, COLUNA_IDENTIFICACAO}, COLUNA_CODIGO + " = ? ",
                    new String[]{String.valueOf(codigo)}, null, null, null, null);

            if( cursor != null){
                cursor.moveToFirst();
            }

            Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            //segue a ordem da  declara~~ao do banco,codigo ,nome,iden,email
            return cliente;
        }
        public void atualizaCliente(Cliente cliente){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUNA_NOME, cliente.getNome());
            values.put(COLUNA_IDENTIFICACAO, cliente.getIdentificao());
            values.put(COLUNA_EMAIL, cliente.getEmail());

            db.update(TABELA_CLIENTE, values, COLUNA_CODIGO + " = ?", new String[] {String.valueOf(cliente.getCodigo())});
        }
        public List<Cliente> listaTodosContatos(){
            List<Cliente> listaClientes = new arrayList<Cliente>();

            String query = "SELECT * FROM " + TABELA_CLIENTE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery(query, null);

            if(c.moveToFirst()){
                do{
                    Cliente cliente = new Cliente();
                    cliente.setNome(c.getString(1));
                    cliente.setIdentificao(c.getString(2));
                    cliente.setEmail(c.getString(3));

                    listaClientes.add(cliente);
                }while(c.moveToNext());
            }
            return ListaClientes;
        }*/




package com.example.cesmac.marlonapplication;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class RepositorioTrabalho {

    protected SQLiteDatabase db;
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS trabalho";
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
            "create table IF NOT EXISTS trabalho (id integer primary key,titulo text not null,disciplina text not null,dificuldade text not null);",
    };
    private static final String NOME_BANCO = "banco_trabalho";
    private static final int VERSAO_BANCO = 1;
    public static final String NOME_TABELA = "trabalho";
    public static final String[] colunas = new String[] {"id","titulo","disciplina","dificuldade"};

    private SQLiteHelper dbHelper;

    public RepositorioTrabalho(Context ctx){
        dbHelper = new SQLiteHelper(ctx, RepositorioTrabalho.NOME_BANCO,RepositorioTrabalho.VERSAO_BANCO,RepositorioTrabalho.SCRIPT_DATABASE_CREATE,RepositorioTrabalho.SCRIPT_DATABASE_DELETE);
        db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
    }

    public RepositorioTrabalho(Context ctx,int i){
        dbHelper = new SQLiteHelper(ctx, RepositorioTrabalho.NOME_BANCO,RepositorioTrabalho.VERSAO_BANCO,RepositorioTrabalho.SCRIPT_DATABASE_CREATE,RepositorioTrabalho.SCRIPT_DATABASE_DELETE);
        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 2);
    }

    protected RepositorioTrabalho(){
    }

    public long id;
    public int count;
    public Trabalho trabalho;
    public Cursor c;

    public long inserir(Trabalho trabalho){
        ContentValues values = new ContentValues();
        values.put("titulo", trabalho.titulo);
        values.put("disciplina", trabalho.disciplina);
        values.put("dificuldade", trabalho.dificuldade);
        id = db.insert(NOME_TABELA, "", values);
        return id;
    }
    public int atualizar(Trabalho trabalho){
        ContentValues values = new ContentValues();
        values.put("id", trabalho.id);
        values.put("titulo", trabalho.titulo);
        values.put("disciplina", trabalho.disciplina);
        values.put("dificuldade", trabalho.dificuldade);
        String _id = String.valueOf(trabalho.id);
        String where = "id" + "=?";
        String[] whereArgs = new String[] {_id};
        count = db.update(NOME_TABELA, values, where, whereArgs);
        return count;
    }
    public int deletar(long id){
        String where = "id" + "=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[] { _id };
        count = db.delete(NOME_TABELA, where, whereArgs);
        return count;
    }
    public Trabalho buscarTrabalho(long id){
        c = db.query(true, NOME_TABELA, colunas, "id" + "=" + id, null, null, null, null, null);
        if(c.getCount() > 0){
            c.moveToFirst();
            trabalho = new Trabalho();
            trabalho.id = c.getLong(0);
            trabalho.titulo = c.getString(1);
            trabalho.disciplina = c.getString(2);
            trabalho.dificuldade = c.getString(3);
            return trabalho;
        }
        return null;
    }

    public List<Trabalho> listarTrabalhos(){
        c = null;
        trabalho = null;
        try{
            c = db.query(NOME_TABELA, colunas, null, null, null, null, null);
        } catch(SQLException e) {
            c = null;
        }

        List<Trabalho> trabalhos = new ArrayList<Trabalho>();
        try {
            if(c.moveToFirst()){
                int idxId = c.getColumnIndex("id");
                int idxLogin = c.getColumnIndex("login");
                do{
                    trabalho = new Trabalho();
                    trabalhos.add(trabalho);
                    trabalho.id = c.getLong(idxId);
                    trabalho.titulo = c.getString(idxLogin);
                    trabalho.disciplina = c.getString(idxLogin);
                    trabalho.dificuldade = c.getString(idxLogin);
                } while (c.moveToNext());
            }
        }catch ( NullPointerException e){
            System.err.print(e);
        }
        return trabalhos;
    }

    public Trabalho buscarTrabalhoPorTitulo(String search_string){
        try{
            c = db.query(NOME_TABELA, RepositorioTrabalho.colunas, "titulo" + "='" + search_string + "'", null, null, null, null);
            if(c.moveToNext()){
                Trabalho trabalho = new Trabalho();
                trabalho.id = c.getLong(0);
                trabalho.titulo = c.getString(1);
                trabalho.disciplina = c.getString(2);
                trabalho.dificuldade = c.getString(3);
            }
        } catch (SQLException e){
            return null;
        }
        return trabalho;
    }

    public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        c = queryBuilder.query(this.db, projection, selection, selectionArgs, groupBy, having, orderBy);
        return c;
    }

    public void fechar(){
        if(db != null){
            db.close();
        }
        if(dbHelper != null){
            dbHelper.close();
        }
    }

    public long salvar(Trabalho trabalho){
        long id = trabalho.id;
        if (id != 0){
            atualizar(trabalho);
        } else {
            id = inserir(trabalho);
        }
        return id;
    }
}

package com.example.cesmac.marlonapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class SQLiteHelper extends SQLiteOpenHelper{
    private String[] scriptSQLCreate;
    private String scriptSQLDelete;

    SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete){
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }
    public void onCreate(SQLiteDatabase db){
        int qtdeScripts = scriptSQLCreate.length;
        for(int i = 0; i < qtdeScripts; i++){
            String sql = scriptSQLCreate[i];
            db.execSQL(sql);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao){
        db.execSQL(scriptSQLDelete);
        onCreate(db);
    }
}
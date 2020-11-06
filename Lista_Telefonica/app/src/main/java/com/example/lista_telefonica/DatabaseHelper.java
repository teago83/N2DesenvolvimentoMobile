package com.example.lista_telefonica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Telefone.db";
    public static final String TABLE_NAME = "contato_table";
    public static final String COL_1 = "nome_contato";
    public static final String COL_2 = "telefone_contato";

    public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(nome_contato STRING, telefone_contato INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertData (String Nome, String Telefone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_1, Nome);
        cv.put(COL_2, Telefone);

        long Result = db.insert(TABLE_NAME, null, cv);

        if (Result == -1)
            return false;
        else
            return true;
    }

    public Cursor ShowData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor Result = db.rawQuery("select * from " + TABLE_NAME, null);
        return Result;
    }
}

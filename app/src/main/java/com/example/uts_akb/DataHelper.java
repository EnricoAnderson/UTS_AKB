package com.example.uts_akb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

//10118353_Enrico Anderson_IF8
//dibuat 6 Juni 2021

public class DataHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "data_diary.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Diary (" +
                "ID INTEGER," +
                "TANGGAL DATETIME DEFAULT (datetime('now','localtime')) NOT NULL," +
                "JUDUL TEXT NOT NULL," +
                "KATEGORI TEXT NOT NULL," +
                "ISI TEXT NOT NULL,"+
                "PRIMARY KEY (ID));";

        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        simpanDataDiary("testjudul", "testkategori", "testisi" );
        simpanDataDiary("testjudul2", "testkategori2", "testis2i" );
        simpanDataDiary("testjudu3l", "testka3tegori", "test3isi" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }


    public void simpanDataDiary(String judul, String kategori, String isi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "INSERT INTO Diary (JUDUL, KATEGORI, ISI) VALUES ('" + judul + "', '" + kategori + "', '" + isi + "')";
        Log.d("Insert admin", "onCreate: " + sql);
        db.execSQL(sql);
    }

    public void editDataDiary(String id, String judul, String kategori, String isi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "UPDATE Diary SET JUDUL='" + judul + "', KATEGORI='" + kategori + "', ISI='" + isi + "' WHERE ID='" + id + "'";
        db.execSQL(sql);
    }

    public void hapusDataDiary(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "DELETE FROM Diary WHERE ID='" + id + "'";
        db.execSQL(sql);
    }

    public ArrayList<Data> tampilDataDiary() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Diary ORDER BY TANGGAL;";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Data> d = new ArrayList<Data>();

        if(cursor.getCount() == 0)
            return d;

        cursor.moveToFirst();

        do {
            String id = cursor.getString(0);
            String tanggal = cursor.getString(1);
            String judul = cursor.getString(2);
            String kategori = cursor.getString(3);
            String isi = cursor.getString(4);

            Data dta = new Data(id, judul, tanggal, kategori, isi);
            d.add(dta);

        } while (cursor.moveToNext());

        cursor.close();

        return d;
    }
}

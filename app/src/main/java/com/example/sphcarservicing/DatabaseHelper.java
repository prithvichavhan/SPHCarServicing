package com.example.sphcarservicing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "Information.db";
    final static int DATABASE_VERSION = 4;
    final static String TABLE1_NAME = "USERS";
    final static String T1COL1 = "Id";
    final static String T1COL2 = "Name";
    final static String T1COL3 = "Email";
    final static String T1COL4 = "Phone";
    final static String T1COL5 = "Password";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME + "( " + T1COL1 + " INTEGER PRIMARY KEY, " +
                T1COL2 + " TEXT," + T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String name,String email, String cell,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,name);
        values.put(T1COL3,email);
        values.put(T1COL4,cell);
        values.put(T1COL5,password);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);

        if(l>0)
            return true;
        else
            return false;
    }


    public Cursor login(String email,String pass){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Email = '"+email+"' AND Password = '"+pass+"'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        return cursor;
    }

    public Cursor viewUsername(String Id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Id = "+Integer.parseInt(Id);
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
}

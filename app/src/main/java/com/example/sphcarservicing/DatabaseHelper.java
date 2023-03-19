package com.example.sphcarservicing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "Information.db";
    final static int DATABASE_VERSION = 6;
    final static String TABLE1_NAME = "USERS";
    final static String T1COL1 = "Email";
    final static String T1COL2 = "Name";
    final static String T1COL3 = "Address";
    final static String T1COL4 = "Phone";
    final static String T1COL5 = "Password";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME + "( " + T1COL1 + " TEXT PRIMARY KEY, " +
                T1COL2 + " TEXT," + T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String email,String name, String address,String cell,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL1,email);
        values.put(T1COL2,name);
        values.put(T1COL3,address);
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

    public boolean updateRec(String email,String newname,String newphone, String newaddress){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,newname);
        values.put(T1COL3,newaddress);
        values.put(T1COL4,newphone);
        int i = sqLiteDatabase.update(TABLE1_NAME,
                values,"Email=?",new String[]{email});
        if(i>0)
            return true;
        else
            return false;
    }
}

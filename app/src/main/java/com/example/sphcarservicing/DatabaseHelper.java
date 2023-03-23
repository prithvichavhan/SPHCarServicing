package com.example.sphcarservicing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "Information.db";
    final static int DATABASE_VERSION = 11;

    //table1
    final static String TABLE1_NAME = "USERS";
    final static String T1COL1 = "Email";
    final static String T1COL2 = "Name";
    final static String T1COL3 = "Address";
    final static String T1COL4 = "Phone";
    final static String T1COL5 = "Password";

    final static String T1COL6 = "Status";

    //table2
    final static String TABLE2_NAME = "Service_provider_information";
    final static String T2COL1 = "CId";
    final static String T2COL2 = "CEmail";
    final static String T2COL3 = "CName";
    final static String T2COL4 = "CCity";
    final static String T2COL5 = "OilChange";
    final static String T2COL6 = "TireChange";
    final static String T2COL7 = "EngineService";
    final static String T2COL8 = "Washing";
    final static String T2COL9 = "BreakChange";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME + "( " + T1COL1 + " TEXT PRIMARY KEY, " +
                T1COL2 + " TEXT," + T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT,"
                + T1COL6 + " TEXT)";

        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TABLE2_NAME + "( " + T2COL1 + " INTEGER PRIMARY KEY, " +
                T2COL3 + " TEXT," +T2COL4 + " TEXT," + T2COL5 + " TEXT," +T2COL6 + " TEXT," +
                T2COL7 + " TEXT," + T2COL8 + " TEXT," + T2COL9 + " TEXT," + T2COL2 + " TEXT," +
                " FOREIGN KEY ("+T2COL2+") REFERENCES "+TABLE1_NAME+" ("+T1COL1+"))";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE2_NAME);
        onCreate(sqLiteDatabase);
    }

    //insert data in service_provider_details
    public boolean addServiceProviderData(String cEmail, String cName, String cCity,
                                 String OilChange, String TireChange, String EngineService,
                                 String Washing, String BreakChange){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2,cEmail);
        values.put(T2COL3,cName);
        values.put(T2COL4,cCity);
        values.put(T2COL5,OilChange);
        values.put(T2COL6,TireChange);
        values.put(T2COL7,EngineService);
        values.put(T2COL8,Washing);
        values.put(T2COL9,BreakChange);

        long l = sqLiteDatabase.insert(TABLE2_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }

    public Cursor viewServiceProviderData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public Cursor viewSpecificServiceProviderData(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME + " WHERE CEmail = '"+email+"'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public Cursor viewSpecificServiceProviderData_compname(String comp_name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME + " WHERE CName = '"+comp_name+"'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }


    public boolean addData(String email,String name, String address,String cell,String password,
                           String status){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL1,email);
        values.put(T1COL2,name);
        values.put(T1COL3,address);
        values.put(T1COL4,cell);
        values.put(T1COL5,password);
        values.put(T1COL6,status);

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

    // for drop table
    public void dropTable(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE2_NAME);
    }

}

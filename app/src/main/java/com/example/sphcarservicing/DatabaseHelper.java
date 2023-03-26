package com.example.sphcarservicing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "Information.db";
    final static int DATABASE_VERSION = 25;

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


    //table3
    final static String TABLE3_NAME = "Bookings";
    final static String T3COL1 = "BId";
    final static String T3COL2 = "SPEmail";
    final static String T3COL3 = "UEMAIL";
    final static String T3COL4 = "BTYPE";
    final static String T3COL5 = "BDATE";

    final static String T3COL6 = "BSERVICES";

    //table4
    final static String TABLE4_NAME = "Service_History";
    final static String T4COL1 = "SId";
    final static String T4COL2 = "SPEmail";
    final static String T4COL3 = "UEMAIL";
    final static String T4COL4 = "BDATE";
    final static String T4COL5 = "BSERVICES";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //t1
        String query = "CREATE TABLE " + TABLE1_NAME + "( " + T1COL1 + " TEXT PRIMARY KEY, " +
                T1COL2 + " TEXT," + T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT,"
                + T1COL6 + " TEXT)";

        sqLiteDatabase.execSQL(query);

        //t2
        query = "CREATE TABLE " + TABLE2_NAME + "( " + T2COL1 + " INTEGER PRIMARY KEY, " +
                T2COL3 + " TEXT," +T2COL4 + " TEXT," + T2COL5 + " TEXT," +T2COL6 + " TEXT," +
                T2COL7 + " TEXT," + T2COL8 + " TEXT," + T2COL9 + " TEXT," + T2COL2 + " TEXT," +
                " FOREIGN KEY ("+T2COL2+") REFERENCES "+TABLE1_NAME+" ("+T1COL1+") ON DELETE CASCADE)";
        sqLiteDatabase.execSQL(query);

        //t3
        query = "CREATE TABLE " + TABLE3_NAME + "( " + T3COL1 + " INTEGER PRIMARY KEY, " +
                T3COL2 + " TEXT," + T3COL3 + " TEXT," + T3COL4 + " TEXT," + T3COL5 + " TEXT,"
                + T3COL6 + " TEXT,"
                + " FOREIGN KEY ("+T3COL2+") REFERENCES "+TABLE2_NAME+" ("+T2COL2+") ON DELETE CASCADE," +
                " FOREIGN KEY ("+T3COL3+") REFERENCES "+TABLE1_NAME+" ("+T1COL1+") ON DELETE CASCADE)";

        sqLiteDatabase.execSQL(query);

        //t4
        query = "CREATE TABLE " + TABLE4_NAME + "( " + T4COL1 + " INTEGER PRIMARY KEY, " +
                T4COL2 + " TEXT," + T4COL3 + " TEXT," + T4COL4 + " TEXT," + T4COL5 + " TEXT,"
                + " FOREIGN KEY ("+T4COL2+") REFERENCES "+TABLE2_NAME+" ("+T2COL2+") ON DELETE CASCADE," +
                " FOREIGN KEY ("+T4COL3+") REFERENCES "+TABLE1_NAME+" ("+T1COL1+") ON DELETE CASCADE )";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE4_NAME);
        onCreate(sqLiteDatabase);
    }


    public Cursor viewUserData(String status){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Status = '"+status+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
    public Cursor viewSpecificUserData(String name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Name = '"+name+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public Cursor checkService(String uemail){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME + " WHERE (CURRENT_DATE > " + T3COL5 + " AND UEMAIL = '"+uemail+"')";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public boolean addServiceHistoryData(String spemail,String uemail, String bdate,
                                  String services){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL2,spemail);
        values.put(T3COL3,uemail);
        values.put(T3COL5,bdate);
        values.put(T3COL6,services);

        long l = sqLiteDatabase.insert(TABLE4_NAME,null,values);

        if(l>0)
            return true;
        else
            return false;
    }
    public Cursor viewServiceHistoryData(String uemail){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME + " WHERE UEMAIL = '"+uemail+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }


    //adding booking data
    public boolean addBookingData(String spemail,String uemail, String btype,String bdate,
                                  String services){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL2,spemail);
        values.put(T3COL3,uemail);
        values.put(T3COL4,btype);
        values.put(T3COL5,bdate);
        values.put(T3COL6,services);

        long l = sqLiteDatabase.insert(TABLE3_NAME,null,values);

        if(l>0)
            return true;
        else
            return false;
    }

    public Cursor viewBookingData(String uemail){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME + " WHERE UEMAIL = '"+uemail+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public boolean deleteBookingData(String uemail,String t3id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long l = sqLiteDatabase.delete(TABLE3_NAME,"UEMAIL=? AND BId=?",new String[]{uemail,t3id});
        if(l>0)
            return true;
        else
            return false;
    }

    public boolean findExistingServiceHistory(String uemail){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE4_NAME + " WHERE (UEMAIL = '"+uemail+"' AND SId = '"+t3id+"')";
        String query = "SELECT * FROM " + TABLE4_NAME + " WHERE UEMAIL = '"+uemail+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
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
    public boolean deleteUserRec(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int i = sqLiteDatabase.delete(TABLE1_NAME,"Email=?",
                new String[]{email});
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

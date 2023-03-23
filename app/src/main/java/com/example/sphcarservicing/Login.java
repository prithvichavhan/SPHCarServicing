package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().clear().commit();

        Button register = findViewById(R.id.btnReg);
        EditText email = findViewById(R.id.editTextTextPersonName);
        EditText pwd = findViewById(R.id.editTextTextPassword);
        Button login = findViewById(R.id.btnLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            String em;
            String pass;
            boolean success;
            @Override
            public void onClick(View view) {
                em = email.getText().toString();
                pass = pwd.getText().toString();

                Cursor cursor = databaseHelper.login(em,pass);
                StringBuilder str1 = new StringBuilder();
                StringBuilder str2 = new StringBuilder();
                StringBuilder str3 = new StringBuilder();
                StringBuilder str4 = new StringBuilder();
                StringBuilder str5 = new StringBuilder();

                if(cursor.getCount()>0) {
                    while (cursor.moveToNext()) {
                        str1.append(cursor.getString(0));
                        str2.append(cursor.getString(1));
                        str3.append(cursor.getString(2));
                        str4.append(cursor.getString(3));
                        str5.append(cursor.getString(5));
                    }
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("EMAIL", String.valueOf(str1));
                    editor.putString("NAME", String.valueOf(str2));
                    editor.putString("ADDRESS", String.valueOf(str3));
                    editor.putString("PHONE", String.valueOf(str4));
                    editor.commit();
                    if(String.valueOf(str5).equals("1")){
                        startActivity(new Intent(Login.this, ServiceProviderHome.class));
                    }
                    else {
                        startActivity(new Intent(Login.this, UserHome.class));
                    }
                }
                else
                    Toast.makeText(Login.this,"Sorry user not found",Toast.LENGTH_SHORT).show();

            }
        });
        // add sample data
        //databaseHelper.dropTable();
        int n;
        boolean isUserAdded = false;
        boolean isProviderAdded = false;
        boolean isProSerAdded = false;
        for (n = 1; n <= 5; n++) {
            String email1 = "user" + n + "@email.com";
            String name1 = "user" + n;
            String address1 = "user" + n + "`s address";
            String phone1 = n + "234567890";
            String password1 = "testUser" + n;
            String email2 = "Provider" + n + "@email.com";
            String name2 = "Provider" + n;
            String address2 = "Provider" + n + "`s address";
            String phone2 = n + "345678901";
            String password2 = "testProvider" + n;
            String name3 = "ServiceProvider" + n;
            String city = "ServiceProvider" + n + "`s city";
            int oil = (int) Math.floor(Math.random() * 2);
            int tire = (int) Math.floor(Math.random() * 2);
            int eng = (int) Math.floor(Math.random() * 2);
            int wash = (int) Math.floor(Math.random() * 2);
            int breakCh = (int) Math.floor(Math.random() * 2);
            int[] services = {oil, tire, eng, wash, breakCh};

            //add users to USERS table
            isUserAdded = databaseHelper.addData(email1,
                    name1, address1, phone1, password1, "0");
            if (isUserAdded) {
                isUserAdded = true;
            } else {
                isUserAdded = false;
                break;
            }
            //add provider to USERS table
            isProviderAdded = databaseHelper.addData(email2,
                    name2, address2, phone2, password2, "1");
            if (isProviderAdded) {
                isProviderAdded = true;
            } else {
                isProviderAdded = false;
                break;
            }
            // add provider to Service_provider_information table
            String[] serviceList = new String[5];
            for (int i = 0; i < services.length; i++) {
                if (services[i] == 0) {
                    serviceList[i] = "NO";
                } else {
                    serviceList[i] = "YES";
                }
            }
            isProSerAdded = databaseHelper.addServiceProviderData(email2, name2, city, serviceList[0],serviceList[1],serviceList[2],serviceList[3],serviceList[4]);
            if (isProSerAdded) {
                isProSerAdded = true;
            } else {
                isProSerAdded = false;
                break;
            }
        }
        if(!isUserAdded){
            Toast.makeText(Login.this, "could not add user sample data", Toast.LENGTH_LONG).show();
        }
        if(!isProviderAdded){
            Toast.makeText(Login.this, "could not add provider sample data", Toast.LENGTH_LONG).show();
        }
        if(!isProSerAdded){
            Toast.makeText(Login.this, "could not add providerServices sample data", Toast.LENGTH_LONG).show();
        }

    }
}
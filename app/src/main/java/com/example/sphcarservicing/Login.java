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
    }
}
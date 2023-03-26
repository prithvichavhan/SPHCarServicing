package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceProviderHome extends AppCompatActivity {
    DatabaseHelper dbh;
    Button alterUser;
    Button deletedUser;
    Button vewEditAppointment;
    Button alterServiceHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_home);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("EMAIL",null);

        dbh = new DatabaseHelper(this);

        Cursor cursor = dbh.viewSpecificServiceProviderData(email);
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                str1.append(cursor.getString(1));
                str2.append(cursor.getString(2));
            }
        }

        TextView cn = findViewById(R.id.txtSPTitle);
        TextView add = findViewById(R.id.txtSPAddress);
        Button log = findViewById(R.id.logout);

        alterUser = findViewById(R.id.alterUser);
        deletedUser = findViewById(R.id.deletedUser);
        vewEditAppointment = findViewById(R.id.vewEditAppointment);
        alterServiceHistory = findViewById(R.id.alterServiceHistory);

        System.out.println(str1.toString());

        cn.setText(str1.toString());
        add.setText(str2.toString());

        cn.setEnabled(false);
        add.setEnabled(false);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().clear().commit();
                Toast.makeText(ServiceProviderHome.this,"You're logging out! Bye.",Toast.LENGTH_SHORT).show();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(ServiceProviderHome.this, Login.class));
                    }
                };

                Timer timer = new Timer();

                timer.schedule(task,1200);
            }
        });

    }
}
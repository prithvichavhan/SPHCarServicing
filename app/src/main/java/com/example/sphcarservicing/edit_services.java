package com.example.sphcarservicing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class edit_services extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_services);

        databaseHelper = new DatabaseHelper(this);

        TextView useremail = findViewById(R.id.userEmail);
        EditText spemail = findViewById(R.id.spEmail);
        EditText bookingdate = findViewById(R.id.bDate);
        EditText bookingservices = findViewById(R.id.bServices);
        Button save = findViewById(R.id.btnDelete);
        Button cancel = findViewById(R.id.btnCancel);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_email = preferences.getString("U_EMAIL",null);
        String sp_email = preferences.getString("EMAIL",null);

        Cursor cursor = databaseHelper.viewSpecificServiceHistoryData(user_email, sp_email);
        StringBuilder bDATE = new StringBuilder();
        StringBuilder bSERVICES = new StringBuilder();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                bDATE.append(cursor.getString(3));
                bSERVICES.append(cursor.getString(4));
                bookingdate.setText(bDATE);
                bDATE.setLength(0);
            }
        }

        useremail.setEnabled(false);
        useremail.setText(user_email);
        spemail.setEnabled(false);
        spemail.setText(sp_email);
        bookingservices.setEnabled(false);
        bookingservices.setText(bSERVICES);

        save.setOnClickListener(new View.OnClickListener() {
            String newbookingdate;

            boolean isUpdated;
            @Override
            public void onClick(View view) {
                newbookingdate = bookingdate.getText().toString();

                isUpdated = databaseHelper.updateServiceRec(useremail.getText().toString(),spemail.getText().toString(),newbookingdate);

                if(isUpdated){
                    Toast.makeText(edit_services.this,"Record is updated",Toast.LENGTH_SHORT).show();

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            preferences.edit().remove("U_EMAIL").commit();
                            startActivity(new Intent(edit_services.this,SpViewServiceHistory.class));
                        }
                    };

                    Timer timer = new Timer();

                    timer.schedule(task,1000);
                }
                else{
                    Toast.makeText(edit_services.this,"Record is not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(edit_services.this,SpViewServiceHistory.class));
            }
        });


    }
}
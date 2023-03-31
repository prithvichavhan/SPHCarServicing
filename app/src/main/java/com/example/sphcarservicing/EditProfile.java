package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class EditProfile extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        databaseHelper = new DatabaseHelper(this);

        TextView email = findViewById(R.id.userEmail);
        EditText name = findViewById(R.id.spEmail);
        EditText phone = findViewById(R.id.bDate);
        EditText address = findViewById(R.id.bServices);
        Button save = findViewById(R.id.btnDelete);
        Button cancel = findViewById(R.id.btnCancel);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_name = preferences.getString("NAME",null);
        String user_email = preferences.getString("EMAIL",null);
        String user_phone = preferences.getString("PHONE",null);
        String user_address = preferences.getString("ADDRESS",null);

        email.setEnabled(false);
        email.setText(user_email);
        name.setText(user_name);
        phone.setText(user_phone);
        address.setText(user_address);

        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        save.setOnClickListener(new View.OnClickListener() {
            String newname;
            String newphone;
            String newaddress;

            boolean isUpdated;
            @Override
            public void onClick(View view) {
                newname = name.getText().toString();
                newphone = phone.getText().toString();
                newaddress = address.getText().toString();


                edit.putString("NAME",newname);
                edit.putString("ADDRESS",newaddress);
                edit.putString("PHONE",newphone);
                edit.apply();

                isUpdated = databaseHelper.updateRec(email.getText().toString(),newname,newphone,newaddress);

                if(isUpdated){
                    Toast.makeText(EditProfile.this,"Record is updated",Toast.LENGTH_SHORT).show();

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(new Intent(EditProfile.this,UserHome.class));
                        }
                    };

                    Timer timer = new Timer();

                    timer.schedule(task,1000);
                }
                else{
                    Toast.makeText(EditProfile.this,"Record is not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile.this,UserHome.class));
            }
        });


    }
}
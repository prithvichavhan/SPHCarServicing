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

public class EditProfile2 extends AppCompatActivity {

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
        String user_name = preferences.getString("U_NAME",null);

        Cursor cursor = databaseHelper.viewSpecificUserData(user_name);
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                str1.append(cursor.getString(0)); //getting email
                str2.append(cursor.getString(3)); //getting phone
                str3.append(cursor.getString(2));
            }
        }


        String user_email = str1.toString();
        String user_phone = str2.toString();
        String user_address = str3.toString();

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
                    Toast.makeText(EditProfile2.this,"Record is updated",Toast.LENGTH_SHORT).show();

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(new Intent(EditProfile2.this,ServiceProviderUserModification.class));
                        }
                    };

                    Timer timer = new Timer();

                    timer.schedule(task,1000);
                }
                else{
                    Toast.makeText(EditProfile2.this,"Record is not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile2.this,ServiceProviderUserModification.class));
            }
        });


    }
}
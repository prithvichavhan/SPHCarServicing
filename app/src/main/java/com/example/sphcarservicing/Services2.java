package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Services2 extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton radioButton1;
    RadioButton radioButton2;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services2);

        dbh = new DatabaseHelper(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String COMPANY_name = preferences.getString("CNAME",null);
        String COMPANY_email = preferences.getString("EMAIL",null);
        String COMPANY_CITY = preferences.getString("CCITY",null);
        String OIL_CHANGE = preferences.getString("OIL_CHANGE",null);
        String TYRE_CHANGE = preferences.getString("TYRE_CHANGE",null);
        String ENGINE_CHANGE = preferences.getString("ENGINE_CHANGE",null);

        radioGroup1 = (RadioGroup) findViewById(R.id.WASHGRP);
        radioGroup2 = (RadioGroup) findViewById(R.id.BRKGRP);

        Button btnSaveData = findViewById(R.id.buttonRegister4);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {
                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();

                radioButton1 = (RadioButton) findViewById(selectedId1);
                radioButton2 = (RadioButton) findViewById(selectedId2);

                isInserted = dbh.addServiceProviderData(COMPANY_email,COMPANY_name,
                        COMPANY_CITY,OIL_CHANGE,TYRE_CHANGE,ENGINE_CHANGE,radioButton1.getText().toString(),
                        radioButton2.getText().toString());
                if(isInserted){
                    Toast.makeText(Services2.this,"Your Services are added, Welcome "+COMPANY_name,
                            Toast.LENGTH_SHORT).show();
                    preferences.edit().clear().commit();
                    startActivity(new Intent(Services2.this,Login.class));
                }
                else {
                    Toast.makeText(Services2.this,"Sorry your services are not added",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
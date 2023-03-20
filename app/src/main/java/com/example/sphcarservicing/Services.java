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

import java.util.Timer;
import java.util.TimerTask;

public class Services extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        SharedPreferences preferences_provider = PreferenceManager.getDefaultSharedPreferences(this);

        radioGroup1 = (RadioGroup) findViewById(R.id.OILGRP);
        radioGroup2 = (RadioGroup) findViewById(R.id.TIREGRP);
        radioGroup3 = (RadioGroup) findViewById(R.id.ENGNGRP);


        Button btnnext = findViewById(R.id.buttonRegister3);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton1 = (RadioButton) findViewById(selectedId1);
                radioButton2 = (RadioButton) findViewById(selectedId2);
                radioButton3 = (RadioButton) findViewById(selectedId3);

                SharedPreferences.Editor editor = preferences_provider.edit();
                editor.putString("OIL_CHANGE", radioButton1.getText().toString());
                editor.putString("TYRE_CHANGE", radioButton2.getText().toString());
                editor.putString("ENGINE_CHANGE", radioButton3.getText().toString());

                editor.commit();

                Toast.makeText(Services.this,
                        "Good, Select More Services",Toast.LENGTH_SHORT).show();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(Services.this, Services2.class));
                    }
                };

                Timer timer = new Timer();

                timer.schedule(task,1000);
            }
        });
    }
}
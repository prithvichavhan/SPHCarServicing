package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.microedition.khronos.egl.EGLDisplay;

public class company_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        EditText mail = findViewById(R.id.editTextTextEmailAddress2);
        EditText company_name = findViewById(R.id.editTextTextPersonName4);
        EditText city = findViewById(R.id.editTextAddress2);
        Button btnnext = findViewById(R.id.buttonRegister2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("EMAIL",null);

        mail.setText(email);
        mail.setEnabled(false);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("CNAME", company_name.getText().toString());
                editor.putString("CCITY", city.getText().toString());
                editor.commit();
                startActivity(new Intent(company_details.this,Services.class));
            }
        });
    }
}
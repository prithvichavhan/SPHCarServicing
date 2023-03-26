package com.example.sphcarservicing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register_user extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        databaseHelper = new DatabaseHelper(this);

        EditText name = findViewById(R.id.editTextTextPersonName2);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText address = findViewById(R.id.editTextAddress);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText password = findViewById(R.id.editPwd);


        Button regist = findViewById(R.id.buttonRegister);


        regist.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {

                isInserted = databaseHelper.addData(email.getText().toString(),
                        name.getText().toString(),address.getText().toString(),phone.getText().toString(),
                        password.getText().toString(),"0");
                if(isInserted){
                    Toast.makeText(Register_user.this,"New User registered",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register_user.this,ServiceProviderUserModification.class));
                }
                else {
                    Toast.makeText(Register_user.this,"Sorry not registered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
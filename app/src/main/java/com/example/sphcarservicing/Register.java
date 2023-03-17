package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        EditText name = findViewById(R.id.editTextTextPersonName2);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText password = findViewById(R.id.editPwd);

        Button login = findViewById(R.id.buttonLoginAsk);
        Button regist = findViewById(R.id.buttonRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {
                isInserted = databaseHelper.addData(name.getText().toString(),
                        email.getText().toString(),phone.getText().toString(),
                        password.getText().toString());
                System.out.println(name.getText());
                System.out.println(email.getText());
                System.out.println(phone.getText());
                System.out.println(password.getText());

                if(isInserted){
                    Toast.makeText(Register.this,"You are registered",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }
                else {
                    Toast.makeText(Register.this,"Sorry not registered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
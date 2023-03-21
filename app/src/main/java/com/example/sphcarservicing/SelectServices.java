package com.example.sphcarservicing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectServices extends AppCompatActivity {

    DatabaseHelper dbh;
    Button scheduleAppointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_services);


        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        // Create Checkbox Dynamically
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(R.string.check_it);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = "You have " + (isChecked ? "checked" : "unchecked") + " this Check it Checkbox.";
                Toast.makeText(SelectServices.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


        Intent intent = getIntent();
        String comp_name = intent.getExtras().getString("CNAME");

        scheduleAppointment = findViewById(R.id.btnScheduleAppointment);

        dbh = new DatabaseHelper(this);

        Cursor cursor = dbh.viewSpecificServiceProviderData_compname(comp_name);
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                str1.append(cursor.getString(1));
                str2.append(cursor.getString(2));
            }
        }

        TextView cn = findViewById(R.id.textServiceTitle);
        TextView add = findViewById(R.id.textServiceAddress);

        cn.setText(str1.toString());
        add.setText(str2.toString());

        cn.setEnabled(false);
        add.setEnabled(false);
    }
}
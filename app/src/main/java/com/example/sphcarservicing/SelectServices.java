package com.example.sphcarservicing;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
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


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_services);


        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        // Create Checkbox Dynamically
//        CheckBox checkBox = new CheckBox(this);
//        checkBox.setText(R.string.check_it);
//        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                String msg = "You have " + (isChecked ? "checked" : "unchecked") + " this Check it Checkbox.";
//                Toast.makeText(SelectServices.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });


        Intent intent = getIntent();
        String comp_name = intent.getExtras().getString("SP_NAME");

        scheduleAppointment = findViewById(R.id.newapp);

        dbh = new DatabaseHelper(this);

        Cursor cursor = dbh.viewSpecificServiceProviderData_compname(comp_name);
        StringBuilder strID = new StringBuilder();
        StringBuilder str0 = new StringBuilder();
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        StringBuilder str4 = new StringBuilder();
        StringBuilder str5 = new StringBuilder();
        StringBuilder str6 = new StringBuilder();
        StringBuilder str7 = new StringBuilder();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                strID.append(cursor.getString(0));//sp_id
                str0.append(cursor.getString(8)); //email
                str1.append(cursor.getString(1));
                str2.append(cursor.getString(2));
                str3.append(cursor.getString(3));
                str4.append(cursor.getString(4));
                str5.append(cursor.getString(5));
                str6.append(cursor.getString(6));
                str7.append(cursor.getString(7));
            }
        }

        TextView cn = findViewById(R.id.textServiceTitle);
        TextView add = findViewById(R.id.textServiceAddress);

        cn.setText(str1.toString());
        add.setText(str2.toString());

//        Log.d(TAG,str0.toString());
//        Log.d(TAG,str1.toString());
//        Log.d(TAG,str2.toString());


        cn.setEnabled(false);
        add.setEnabled(false);

        CheckBox ch;
        for (int i=0;i<5;i++){
            ch = new CheckBox(this);
            if(i==0 && str3.toString().equals("YES")){
                ch.setText("OIL CHANGE");
                ch.setId(0);
                linearLayout.addView(ch);
            }
            if(i==1 && str4.toString().equals("YES")){
                ch.setText("TYRE CHANGE");
                ch.setId(1);
                linearLayout.addView(ch);
            }
            if(i==2 && str5.toString().equals("YES")){
                ch.setText("ENGINE SERVICE");
                ch.setId(2);
                linearLayout.addView(ch);
            }
            if(i==3 && str6.toString().equals("YES")){
                ch.setText("WASHING");
                ch.setId(3);
                linearLayout.addView(ch);
            }
            if(i==4 && str7.toString().equals("YES")){
                ch.setText("BREAK CHANGE");
                ch.setId(4);
                linearLayout.addView(ch);
            }

            CheckBox finalCh = ch;

            ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String msg = "You have " + (isChecked ? "selected " + finalCh.getId() : "unchecked") ;
//                    Toast.makeText(SelectServices.this, msg, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, msg);
                }
            });
        }

        Button btnScheduleAppointment = findViewById(R.id.newapp);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        btnScheduleAppointment.setOnClickListener(new View.OnClickListener() {
            String abc="";
            @Override
            public void onClick(View view) {

                for(int i=0;i<5;i++){
                    try{
                        CheckBox ch = (CheckBox) findViewById(i);
                        if (ch.isChecked()) {
                            abc = abc + " " + ch.getText();
                        }
                    }
                    catch (Exception ex){
                        System.out.println(ex);
                    }
                }


                editor.putString("SP_ID", strID.toString());
                editor.putString("SP_EMAIL", str0.toString());
                editor.putString("SERVICES", abc);
                editor.commit();


//                Toast.makeText(SelectServices.this,abc,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SelectServices.this,ScheduleAppointment.class));
            }
        });

    }
}
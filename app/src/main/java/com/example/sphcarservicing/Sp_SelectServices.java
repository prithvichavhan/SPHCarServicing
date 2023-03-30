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

public class Sp_SelectServices extends AppCompatActivity {

    DatabaseHelper dbh;
    Button scheduleAppointment_new;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_select_services);


        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Integer bookin_id = preferences.getInt("BOOKING_ID",10000);

        dbh = new DatabaseHelper(this);

        Cursor cursor1 = dbh.viewBookingDataSP(bookin_id);
        StringBuilder comp_email = new StringBuilder();
        StringBuilder user_email = new StringBuilder();
        if(cursor1.getCount()>0) {
            while (cursor1.moveToNext()) {
                comp_email.append(cursor1.getString(1));
                user_email.append(cursor1.getString(2));
            }
        }

        Cursor cursor2 = dbh.viewSpecificServiceUserData(user_email.toString());
        StringBuilder user_name = new StringBuilder();
        if(cursor2.getCount()>0) {
            while (cursor2.moveToNext()) {
                user_name.append(cursor1.getString(1));
            }
        }

        Cursor cursor = dbh.viewSpecificServiceProviderData(comp_email.toString());
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

        cn.setText(user_name);
        add.setText(user_email);

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

        scheduleAppointment_new = findViewById(R.id.newapp);



        SharedPreferences.Editor editor = preferences.edit();

        scheduleAppointment_new.setOnClickListener(new View.OnClickListener() {
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

                editor.putString("NEW_SERVICES", abc);
                editor.commit();


                Toast.makeText(Sp_SelectServices.this,abc,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Sp_SelectServices.this,Sp_ScheduleAppointment.class));
            }
        });

    }
}
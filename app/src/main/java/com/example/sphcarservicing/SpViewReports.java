package com.example.sphcarservicing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpViewReports extends AppCompatActivity implements spViewReports_Adapter.ItemClickListener{

    spViewReports_Adapter adapter;
    DatabaseHelper dbh;

    ArrayList<spViewReports_Model> UserViewAppointment_Model_ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spviewreports);

        Button sp_home_button = findViewById(R.id.sp_homeB3);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sp_email = preferences.getString("EMAIL",null);

        sp_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SpViewReports.this,ServiceProviderHome.class));
            }
        });

        UserViewAppointment_Model_ArrayList = new ArrayList<>();

        dbh = new DatabaseHelper(this);

        Cursor cursor = dbh.spviewBookingData(sp_email);
        StringBuilder str0 = new StringBuilder();
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        StringBuilder str4 = new StringBuilder();
        StringBuilder str5 = new StringBuilder();
        StringBuilder str6 = new StringBuilder();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                str0.append(cursor.getString(0));
                str1.append(cursor.getString(2)); //getting email of user
                str3.append(cursor.getString(4)); //date
                str4.append(cursor.getString(3)); //type
                str5.append(cursor.getString(5)); //services


                System.out.println(str1);
                Cursor cursor1 = dbh.viewSpecificServiceUserData(str1.toString());
                StringBuilder sp_details = new StringBuilder();
                StringBuilder sp_details1 = new StringBuilder();
                if (cursor1.getCount() > 0) {
                    while (cursor1.moveToNext()) {
                        sp_details.append(cursor1.getString(1));
                        sp_details1.append(cursor1.getString(2));
                    }
                }

                System.out.println(sp_details + " "+ sp_details1 + " "+Integer.parseInt(str0.toString()));
                str2.append(sp_details); //user name
                str6.append(sp_details1); //user city
//                UserViewAppointment_Model_ArrayList.add(new
//                        spViewReports_Model(String.valueOf(str2),String.valueOf(str6),
//                        String.valueOf(str3),String.valueOf(str4),String.valueOf(str5)));

                str0.setLength(0);
                str1.setLength(0);
                str2.setLength(0);
                str3.setLength(0);
                str4.setLength(0);
                str5.setLength(0);
                str6.setLength(0);
                sp_details.setLength(0);
                sp_details1.setLength(0);
            }
            RecyclerView recyclerView = findViewById(R.id.recyclerView1);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new spViewReports_Adapter(UserViewAppointment_Model_ArrayList,
                    this, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UNAME", adapter.getItem1(position));
        editor.putString("UEMAIL", adapter.getItem2(position));
        editor.commit();

        startActivity(new Intent(SpViewReports.this,Sp_SelectServices.class));
    }
}
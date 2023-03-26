package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import java.util.ArrayList;

public class ViewAppointments extends AppCompatActivity implements UserViewAppointment_Adapter.ItemClickListener{

    UserViewAppointment_Adapter adapter;
    DatabaseHelper dbh;

    ArrayList<UserViewAppointment_Model> UserViewAppointment_Model_ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_email = preferences.getString("EMAIL",null);

        UserViewAppointment_Model_ArrayList = new ArrayList<>();

        dbh = new DatabaseHelper(this);
        Cursor cursor = dbh.viewBookingData(user_email);
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        StringBuilder str4 = new StringBuilder();
        StringBuilder str5 = new StringBuilder();
        StringBuilder str6 = new StringBuilder();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                str1.append(cursor.getString(1)); //getting email of sp

                str3.append(cursor.getString(4)); //date
                str4.append(cursor.getString(3)); //type
                str5.append(cursor.getString(5)); //services


                Cursor cursor1 = dbh.viewSpecificServiceProviderData(str1.toString());
                StringBuilder sp_details = new StringBuilder();
                StringBuilder sp_details1 = new StringBuilder();
                if (cursor1.getCount() > 0) {
                    while (cursor1.moveToNext()) {
                        sp_details.append(cursor1.getString(2));
                        sp_details1.append(cursor1.getString(1));
                    }
                }

                str2.append(sp_details); //sp name
                str6.append(sp_details1); //sp city
                UserViewAppointment_Model_ArrayList.add(new
                        UserViewAppointment_Model(String.valueOf(str6),String.valueOf(str2),
                        String.valueOf(str3),String.valueOf(str4),String.valueOf(str5)));

                str1.setLength(0);
                str2.setLength(0);
                str3.setLength(0);
                str4.setLength(0);
                str5.setLength(0);
                str6.setLength(0);
                sp_details.setLength(0);
                sp_details1.setLength(0);

            }


            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new UserViewAppointment_Adapter(UserViewAppointment_Model_ArrayList,
                    this, this);
            recyclerView.setAdapter(adapter);
        }
    }
    @Override
    public void onItemClick(View view, int position) {

    }
}
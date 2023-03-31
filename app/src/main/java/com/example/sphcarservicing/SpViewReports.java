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


        Cursor viewServiceHistoryData = dbh.viewServiceHistoryData(sp_email);
        StringBuilder spemail = new StringBuilder();
        StringBuilder uemail = new StringBuilder();
        StringBuilder bdate = new StringBuilder();
        StringBuilder bservices = new StringBuilder();
        if (viewServiceHistoryData.getCount() > 0) {
            while (viewServiceHistoryData.moveToNext()) {
                spemail.append(viewServiceHistoryData.getString(1));
                uemail.append(viewServiceHistoryData.getString(2));
                bdate.append(viewServiceHistoryData.getString(3));
                bservices.append(viewServiceHistoryData.getString(4));

                Cursor viewSpecificServiceUserData = dbh.viewSpecificServiceUserData(uemail.toString());
                StringBuilder uname = new StringBuilder();
                if (viewSpecificServiceUserData.getCount() > 0) {
                    while (viewSpecificServiceUserData.moveToNext()) {
                        uname.append(viewSpecificServiceUserData.getString(1));
                    }
                }
                Cursor viewSpecificServiceProviderData = dbh.viewSpecificServiceProviderData(uemail.toString());
                StringBuilder spname = new StringBuilder();
                if (viewSpecificServiceProviderData.getCount() > 0) {
                    while (viewSpecificServiceProviderData.moveToNext()) {
                        spname.append(viewSpecificServiceProviderData.getString(1));
                    }
                }

                dbh.addServiceReports(uname.toString(),uemail.toString(),spname.toString(),
                        spemail.toString(),bservices.toString(),bdate.toString());

                spemail.setLength(0);
                uemail.setLength(0);
                bdate.setLength(0);
                bservices.setLength(0);
                uname.setLength(0);
                spname.setLength(0);
            }
        }




        Cursor cursor = dbh.viewSpecificServiceReportData(sp_email);
        StringBuilder str0 = new StringBuilder();
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        StringBuilder str4 = new StringBuilder();
        StringBuilder str5 = new StringBuilder();
        StringBuilder str6 = new StringBuilder();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                str0.append(cursor.getString(0));//id
                str1.append(cursor.getString(2)); //uname
                str2.append(cursor.getString(3)); //uemail
                str3.append(cursor.getString(3)); //spname
                str4.append(cursor.getString(3)); //spemail
                str5.append(cursor.getString(5)); //services
                str6.append(cursor.getString(5)); //bdate

                UserViewAppointment_Model_ArrayList.add(new
                        spViewReports_Model(String.valueOf(str1),String.valueOf(str2),
                        String.valueOf(str6),String.valueOf(str5)));

                str0.setLength(0);
                str1.setLength(0);
                str2.setLength(0);
                str3.setLength(0);
                str4.setLength(0);
                str5.setLength(0);
                str6.setLength(0);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new spViewReports_Adapter(UserViewAppointment_Model_ArrayList,
                this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UNAME", adapter.getItem1(position));
        editor.putString("UEMAIL", adapter.getItem2(position));
        editor.commit();

//        startActivity(new Intent(SpViewReports.this,Sp_SelectServices.class));
    }
}
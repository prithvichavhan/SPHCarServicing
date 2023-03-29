package com.example.sphcarservicing;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewServiceHistory extends AppCompatActivity implements ServiceHistory_Adapter.ItemClickListener{
    DatabaseHelper dbh;
    ServiceHistory_Adapter adapter;
    ArrayList<ServiceHistory_Model> ServiceHistory_Model_ArrayList;
    public static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service_history);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_email = preferences.getString("EMAIL",null);

        ServiceHistory_Model_ArrayList = new ArrayList<>();

        dbh = new DatabaseHelper(this);

        Cursor maincursor = dbh.viewServiceHistoryData(user_email);

        count = maincursor.getCount();
        System.out.println(count);
        if(maincursor.getCount() > count){
            //take data from service history
            tempfunc(user_email);

        }
        else{
            Cursor cursor = dbh.checkService(user_email);
            StringBuilder str0 = new StringBuilder();
            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();
            StringBuilder str4 = new StringBuilder();
            StringBuilder str5 = new StringBuilder();
            StringBuilder str6 = new StringBuilder();

            boolean isInserted;
            boolean isDeleted;
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    str0.append(cursor.getString(0));
                    str1.append(cursor.getString(1));
                    str2.append(cursor.getString(2));
                    str4.append(cursor.getString(4));
                    str5.append(cursor.getString(5));
                    str6.append(cursor.getString(6)); //taking out booking id from booking

                    Log.d(TAG,str1.toString()+str2.toString()+str4.toString()+str5.toString());
                }
                isInserted = dbh.addServiceHistoryData(str1.toString(),str2.toString(),str4.toString(),str5.toString(),str6.toString());

                if(isInserted){
                    Toast.makeText(ViewServiceHistory.this,"Data for service history is entered",Toast.LENGTH_SHORT).show();
                    count += 1;
                    RecyclerView recyclerView = findViewById(R.id.recyclerView1);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ServiceHistory_Adapter(ServiceHistory_Model_ArrayList,
                            this, this);
                    recyclerView.setAdapter(adapter);
                    isDeleted = dbh.deleteBookingData(user_email,str0.toString());
                    if(isDeleted){
                        Log.d(TAG,"Booking data is deleted..");
                    }
                    else {
                        Log.d(TAG,"NOT deleted BOOKIN DATA..");
                    }
                }
                else {
                    Toast.makeText(ViewServiceHistory.this,"Sorry not registered",Toast.LENGTH_SHORT).show();
                    RecyclerView recyclerView = findViewById(R.id.recyclerView1);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ServiceHistory_Adapter(ServiceHistory_Model_ArrayList,
                            this, this);
                    recyclerView.setAdapter(adapter);
                }

            }
            else {
                Toast.makeText(this,"Service History",Toast.LENGTH_SHORT).show();
                System.out.println(count);
                RecyclerView recyclerView = findViewById(R.id.recyclerView1);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new ServiceHistory_Adapter(ServiceHistory_Model_ArrayList,
                        this, this);
                recyclerView.setAdapter(adapter);
            }


            //take data from service history
            tempfunc(user_email);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public void tempfunc(String user_email) {
        Cursor cursor1 = dbh.viewServiceHistoryData(user_email);
        StringBuilder str11 = new StringBuilder();
        StringBuilder str12 = new StringBuilder(); //name
        StringBuilder str13 = new StringBuilder(); //city
        StringBuilder str14 = new StringBuilder();
        StringBuilder str15 = new StringBuilder();

        if (cursor1.getCount() > 0) {
            while (cursor1.moveToNext()) {
                str11.append(cursor1.getString(1)); //email of sp from history table
                str14.append(cursor1.getString(3)); //bdate history table
                str15.append(cursor1.getString(4)); //services from history table


                Cursor cursor2 = dbh.viewSpecificServiceProviderData(str11.toString());
                StringBuilder sp_details = new StringBuilder();
                StringBuilder sp_details1 = new StringBuilder();
                if (cursor2.getCount() > 0) {
                    while (cursor2.moveToNext()) {
                        sp_details.append(cursor2.getString(1)); //name
                        sp_details1.append(cursor2.getString(2)); //city1
                    }
                }
                str12.append(sp_details); //sp name
                str13.append(sp_details1); //sp city

                ServiceHistory_Model_ArrayList.add(new
                        ServiceHistory_Model(String.valueOf(str12), String.valueOf(str13),
                        String.valueOf(str14), String.valueOf(str15)));

                str11.setLength(0);
                str12.setLength(0);
                str13.setLength(0);
                str14.setLength(0);
                str15.setLength(0);
                sp_details.setLength(0);
                sp_details1.setLength(0);

            }

            System.out.println(count);
            RecyclerView recyclerView = findViewById(R.id.recyclerView1);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ServiceHistory_Adapter(ServiceHistory_Model_ArrayList,
                    this, this);
            recyclerView.setAdapter(adapter);
        }
    }
}
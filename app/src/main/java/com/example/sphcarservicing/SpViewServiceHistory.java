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

public class SpViewServiceHistory extends AppCompatActivity implements Sp_ServiceHistory_Adapter.ItemClickListener{
    DatabaseHelper dbh;
    Sp_ServiceHistory_Adapter adapter;
    ArrayList<Sp_ServiceHistory_Model> ServiceHistory_Model_ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_view_service_history);

        Button sp_homeB2 = findViewById(R.id.sp_homeB2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sp_email = preferences.getString("EMAIL",null);

        ServiceHistory_Model_ArrayList = new ArrayList<>();

        dbh = new DatabaseHelper(this);

        sp_homeB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SpViewServiceHistory.this,ServiceProviderHome.class));
            }
        });

        tempfunc(sp_email);
    }

    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("U_EMAIL", adapter.getItem(position));
        editor.commit();

        startActivity(new Intent(SpViewServiceHistory.this,edit_services.class));

    }

    public void tempfunc(String sp_email) {

        Cursor cursor1 = dbh.viewServiceHistoryDataSP(sp_email);
        StringBuilder str11 = new StringBuilder();
        StringBuilder str12 = new StringBuilder(); //name
        StringBuilder str13 = new StringBuilder(); //city
        StringBuilder str14 = new StringBuilder();
        StringBuilder str15 = new StringBuilder();

        if (cursor1.getCount() > 0) {
            while (cursor1.moveToNext()) {
                str11.append(cursor1.getString(2)); //email of user from history table
                str14.append(cursor1.getString(3)); //bdate history table
                str15.append(cursor1.getString(4)); //services from history table


                Cursor cursor2 = dbh.viewSpecificServiceUserData(str11.toString()); //info of the user
                StringBuilder user_details = new StringBuilder();
                StringBuilder user_details1 = new StringBuilder();
                if (cursor2.getCount() > 0) {
                    while (cursor2.moveToNext()) {
                        user_details.append(cursor2.getString(1)); //name
                        user_details1.append(cursor2.getString(2)); //city1
                    }
                }
                str12.append(user_details); //user name
                str13.append(user_details1); //user city

                ServiceHistory_Model_ArrayList.add(new
                        Sp_ServiceHistory_Model(String.valueOf(str12), String.valueOf(str13),
                        String.valueOf(str14), String.valueOf(str15),String.valueOf(str11)));

                str11.setLength(0);
                str12.setLength(0);
                str13.setLength(0);
                str14.setLength(0);
                str15.setLength(0);
                user_details.setLength(0);
                user_details1.setLength(0);

            }

            RecyclerView recyclerView = findViewById(R.id.recyclerView1234);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Sp_ServiceHistory_Adapter(ServiceHistory_Model_ArrayList,
                    this, this);
            recyclerView.setAdapter(adapter);
        }
    }
}
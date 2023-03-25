package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
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

        UserViewAppointment_Model_ArrayList = new ArrayList<>();

        dbh = new DatabaseHelper(this);
        Cursor cursor = dbh.viewBookingData();
        StringBuilder str1 = new StringBuilder();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                str1.append(cursor.getString(1));
                UserViewAppointment_Model_ArrayList.add(new
                        UserViewAppointment_Model(String.valueOf(str1)));
                str1.setLength(0);
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
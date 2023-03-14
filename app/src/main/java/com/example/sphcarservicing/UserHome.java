package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        //Declare Buttons
        Button editProfile = findViewById(R.id.btnEditProfile);
        Button searchServiceProviderMain = findViewById(R.id.btnSearchSP);
        Button bookAppointment = findViewById(R.id.btnBookApt);
        Button viewAppointment = findViewById(R.id.btnViewApt);
        Button viewServiceHistory = findViewById(R.id.btnViewHistory);

        //Launch Edit Profile Activity
        editProfile.setOnClickListener(view -> startActivity(new Intent(UserHome.this, EditProfile.class)));

        //Launch Search Service Provider Activity
        searchServiceProviderMain.setOnClickListener(view -> startActivity(new Intent(UserHome.this, SearchServiceProvider.class)));

        //Launch Book Appointment Activity (launches Search Service Provider as mentioned in project guidelines)
        bookAppointment.setOnClickListener(view -> startActivity(new Intent(UserHome.this, SearchServiceProvider.class)));

        //Launch View Appointment Activity
        viewAppointment.setOnClickListener(view -> startActivity(new Intent(UserHome.this, ViewAppointments.class)));
    }
}
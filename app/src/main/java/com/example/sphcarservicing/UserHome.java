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

        Button editProfile = findViewById(R.id.btnEditProfile);
        Button searchServiceProviderMain = findViewById(R.id.btnSearchSP);
        Button viewAppointment = findViewById(R.id.btnViewApt);

        editProfile.setOnClickListener(view -> startActivity(new Intent(UserHome.this, EditProfile.class)));

        searchServiceProviderMain.setOnClickListener(view -> startActivity(new Intent(UserHome.this, SearchServiceProvider.class)));

        viewAppointment.setOnClickListener(view -> startActivity(new Intent(UserHome.this, ViewAppointments.class)));

    }
}
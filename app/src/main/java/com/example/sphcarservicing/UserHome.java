package com.example.sphcarservicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class UserHome extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        databaseHelper = new DatabaseHelper(this);

        //Declare Buttons
        Button editProfile = findViewById(R.id.btnEditProfile);
        Button searchServiceProviderMain = findViewById(R.id.btnSearchSP);
        Button bookAppointment = findViewById(R.id.btnBookApt);
        Button viewAppointment = findViewById(R.id.btnViewApt);
        Button viewServiceHistory = findViewById(R.id.btnViewHistory);
        Button logout = findViewById(R.id.btnLogout);
        Button notifications = findViewById(R.id.notifications);
        TextView name = findViewById(R.id.txtName);
        ImageView userimg = findViewById(R.id.imgAvatar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("NAME",null);
        name.setText(username);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().clear().commit();
                Toast.makeText(UserHome.this,"You're logging out! Bye.",Toast.LENGTH_SHORT).show();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(UserHome.this, Login.class));
                    }
                };

                Timer timer = new Timer();

                timer.schedule(task,1200);
            }
        });

        userimg.setImageResource(R.drawable.newuser);

        //Launch Edit Profile Activity
        editProfile.setOnClickListener(view -> startActivity(new Intent(UserHome.this, EditProfile.class)));

        //Launch Search Service Provider Activity
        searchServiceProviderMain.setOnClickListener(view -> startActivity(new Intent(UserHome.this, SearchServiceProvider.class)));

        //Launch Book Appointment Activity (launches Search Service Provider as mentioned in project guidelines)
        bookAppointment.setOnClickListener(view -> startActivity(new Intent(UserHome.this, SearchServiceProvider.class)));

        //Launch View Appointment Activity
        viewAppointment.setOnClickListener(view -> startActivity(new Intent(UserHome.this, ViewAppointments.class)));

        //Launch View Service History Activity
        viewServiceHistory.setOnClickListener(view -> startActivity(new Intent(UserHome.this, ViewServiceHistory.class)));

        //Launch View Service History Activity
        notifications.setOnClickListener(view -> startActivity(new Intent(UserHome.this, ViewNotifications.class)));
    }
}
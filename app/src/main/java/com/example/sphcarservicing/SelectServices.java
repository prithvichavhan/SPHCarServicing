package com.example.sphcarservicing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectServices extends AppCompatActivity {

    Button scheduleAppointment = findViewById(R.id.btnScheduleAppointment);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_services);

        scheduleAppointment.setOnClickListener(view -> startActivity(new Intent(SelectServices.this, ScheduleAppointment.class)));
    }
}
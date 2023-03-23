package com.example.sphcarservicing;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ScheduleAppointment extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);


        Button buttonBookAppointment = findViewById(R.id.buttonBookAppointment);
        CalendarView calendarView = findViewById(R.id.calendarView);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        final String[] currdate = new String[1];
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                currdate[0] = i2 + "/" + (i1+1) + "/" + i; //dd//mm//yyyy
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy: "+currdate[0]);
            }
        });


        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(ScheduleAppointment.this,"Booking Date is "+currdate[0]
                        +
                        " and Service Type "+
                        radioButton.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
}
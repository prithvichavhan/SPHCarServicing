package com.example.sphcarservicing;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Sp_ScheduleAppointment extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_schedule_appointment);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String NEW_SERVICES = preferences.getString("NEW_SERVICES",null);
        Integer bookin_id = preferences.getInt("BOOKING_ID",10000);

        dbh = new DatabaseHelper(this);


        Button buttonBookAppointment = findViewById(R.id.buttonBookAppointment);
        CalendarView calendarView = findViewById(R.id.calendarView);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        final String[] currdate = new String[1];
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
//                currdate[0] = i2 + "/" + (i1+1) + "/" + i; //dd//mm//yyyy

                String selectedDate = String.format("%d-%02d-%02d", i, i1 + 1, i2);
                currdate[0] = selectedDate;

//                currdate[0] = i + "-" + (i1+1) + "-" + i2; //yyyy//mm//dd
                Log.d(TAG, "onSelectedDayChange : "+currdate[0]);

            }
        });


        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            String btype;
            boolean isInserted;
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

//                Toast.makeText(ScheduleAppointment.this,"Booking Date is "+currdate[0]
//                        +
//                        " and Service Type "+
//                        radioButton.getText().toString(),Toast.LENGTH_LONG).show();

                btype = radioButton.getText().toString();

                isInserted = dbh.updateBookingRec(bookin_id.toString(),NEW_SERVICES,currdate[0],btype);

                if(isInserted){
                    Toast.makeText(Sp_ScheduleAppointment.this,"Appointment has been" +
                            "Rescheduled!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sp_ScheduleAppointment.this,ServiceProviderHome.class));
                }
                else {
                    Toast.makeText(Sp_ScheduleAppointment.this,"Sorry Appointment not" +
                            " Rescheduled, " +
                            "try again later!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sp_ScheduleAppointment.this,ServiceProviderHome.class));
                }
            }
        });


    }
}
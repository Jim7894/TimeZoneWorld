package com.example.jimjohansson.timezoneworld;


import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class OwnTimeActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener, AdapterView.OnItemSelectedListener {

    int timePickedHour;
    int timePickedMinutes;
    int timeZoneSelected = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_time);

        TimePicker timePicker = (TimePicker) findViewById(R.id.timepicker);
        timePicker.setOnTimeChangedListener(this);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

       // timePicker = new TimePickerDialog(mContext, R.style.TimePickerTheme, fromListener, hour, min, false);



    }


    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {



        timePickedHour = i;
        timePickedMinutes = i1;
        setConvertedTime();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        timeZoneSelected = position;
        setConvertedTime();

    }


    public void setConvertedTime() {

        TextView tdate = (TextView) findViewById(R.id.textView3);

        Resources res = getResources(); //assuming in an activity
        String[] zonesItems = res.getStringArray(R.array.zone_name);


        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, timePickedHour);
        cal.set(Calendar.MINUTE, timePickedMinutes);

        Date date = cal.getTime();


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        sdf.setTimeZone(TimeZone.getTimeZone(zonesItems[timeZoneSelected]));

        String dateString = sdf.format(date);
        tdate.setText(dateString);



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}

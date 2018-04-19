package com.example.jimjohansson.timezoneworld;

import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class OwnTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    String timepickerChoose = null;
    int timePickedHour;
    int timePickedMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_time);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

                                      @Override

                                      public void onClick(View v) {


                                          DialogFragment timePicker = new TimePickerFragment();

                                          timePicker.show(getSupportFragmentManager(), "timePicker");

                                      }

                                  });

       Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener( this);



        }

    @Override
    public void onTimeSet(TimePicker view, int houOfDay, int minute) {
        TextView textView = (TextView) findViewById (R.id.textView3);
        textView.setText("Hour: " + houOfDay + " Minute: " + minute);


        timePickedHour = houOfDay;
        timePickedMinutes = minute;
        timepickerChoose =  houOfDay + " " + minute;





    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, View view, final int position, long l) {

        Log.d("David", "onItemSelected: ");
        if (timepickerChoose != null) {
            final String text = parent.getItemAtPosition(position).toString();
            //showing selected spinner item


            // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();


            TextView tdate = (TextView) findViewById(R.id.textView3);

            Resources res = getResources(); //assuming in an activity
            String[] zonesItems = res.getStringArray(R.array.zone_name);


            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.HOUR_OF_DAY, timePickedHour );
            cal.set(Calendar.MINUTE, timePickedMinutes );

            Date date = cal.getTime();

            // long date = System.currentTimeMillis();

            SimpleDateFormat sdf = new SimpleDateFormat("HH mm");

            sdf.setTimeZone(TimeZone.getTimeZone(zonesItems[position]));


            String dateString = sdf.format(date);
            tdate.setText(dateString);
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}







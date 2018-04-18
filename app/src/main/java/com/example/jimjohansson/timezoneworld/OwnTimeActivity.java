package com.example.jimjohansson.timezoneworld;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


public class OwnTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {




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
        


        }

    @Override
    public void onTimeSet(TimePicker view, int houOfDay, int minute) {
        TextView textView = (TextView) findViewById (R.id.textView3);
        textView.setText("Hour: " + houOfDay + " Minute: " + minute);
    }
}







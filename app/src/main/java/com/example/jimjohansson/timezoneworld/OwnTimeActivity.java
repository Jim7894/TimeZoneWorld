package com.example.jimjohansson.timezoneworld;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class OwnTimeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_time);

        Spinner spinner = findViewById(R.id.spinnersecond);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(final AdapterView<?> parent, View view, final int position, long l) {

        final String text = parent.getItemAtPosition(position).toString();
        //showing selected spinner item


        // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();


        TextView tdate = (TextView) findViewById(R.id.timeresponse);

        Resources res = getResources(); //assuming in an activity
        String[] zonesItems = res.getStringArray(R.array.zone_name);


        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy\nhh-mm-ss a");

        sdf.setTimeZone(TimeZone.getTimeZone(zonesItems[position]));

        String dateString = sdf.format(date);
        tdate.setText(dateString);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    }





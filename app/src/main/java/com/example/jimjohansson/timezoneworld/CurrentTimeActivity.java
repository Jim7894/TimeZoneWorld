package com.example.jimjohansson.timezoneworld;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import java.util.TimeZone;

public class CurrentTimeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_time);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.zones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.time);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy\nhh-mm-ss a");
                                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);

                                Resources res = getResources(); //assuming in an activity 
                                String[] zonesItems = res.getStringArray(R.array.zones);
                                String Finland = zonesItems[1];

                                if(Finland.equals(true)){
                                    sdf.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));

                                }




                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };

        t.start();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

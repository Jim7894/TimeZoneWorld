package com.example.jimjohansson.timezoneworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UsersActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}

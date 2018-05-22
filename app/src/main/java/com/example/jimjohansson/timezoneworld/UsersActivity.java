package com.example.jimjohansson.timezoneworld;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.TimeZone;


public class UsersActivity extends AppCompatActivity{

ArrayList<String> myArrayList = new ArrayList<>();


Firebase myfirebase;
ListView myListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //Firebase.setAndroidContext(this);
        //myfirebase = new Firebase("https://firecast-app-1c0c5.firebaseio.com/");

        DatabaseReference myfirebase = FirebaseDatabase.getInstance().getReference();


        final ArrayAdapter<String> myArrayadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);


        myListView = (android.widget.ListView)findViewById(R.id.ListView);
        myListView.setAdapter(myArrayadapter);

        myfirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren() ) {
                    String name = data.child("name").getValue(String.class);
                    String timezone  = data.child("timezone").getValue(String.class);

                    myArrayList.add(name + "  " + timezone);

                }
                myArrayadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Log.d("Jim", "String");
    }

}

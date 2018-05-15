package com.example.jimjohansson.timezoneworld;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MenuActivity extends AppCompatActivity implements ValueEventListener {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    //private DatabaseReference UserIDReference = mRootReference.child("Uid");
  //  private DatabaseReference timezoneReference = mRootReference.child("timezone");

    int Uid;


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button n=(Button) findViewById(R.id.currenttime);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Roboto-Medium.ttf");

        n.setTypeface(typeface);

        button = (Button) findViewById(R.id.logout);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                } else {


                    String uid = firebaseAuth.getCurrentUser().getUid();
                    String name = firebaseAuth.getCurrentUser().getDisplayName();

                   // UserIDReference.setValue(Uid);


                    long date = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy\nhh-mm-ss a");

                    TimeZone tz = TimeZone.getDefault();
                    sdf.setTimeZone(tz);

                    String dateString = sdf.format(date);

                    mRootReference.child(uid).child("name").setValue(name);
                    mRootReference.child(uid).child("timezone").setValue(tz);

                    //timezoneReference.setValue(tz);

                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
            }
        });

        Button btn = (Button) findViewById(R.id.currenttime);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, CurrentTimeActivity.class));

            }
        });

        Button btn2 = (Button) findViewById(R.id.owntime);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, OwnTimeActivity.class));

            }
        });

        Button btn3 = (Button) findViewById(R.id.users);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, UsersActivity.class));

            }
        });
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}








package com.example.jimjohansson.timezoneworld;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;

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

        button = (Button)findViewById(R.id.logout);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MenuActivity.this,LoginActivity.class));
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            auth.signOut();
            }
        });
    }

   /* public void signOut(){
        auth.signOut();
        finish();
        Intent i = new Intent(this, LoginActivity.class);
    }*/
}

package com.example.kar.horoscope_world;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        auth = FirebaseAuth.getInstance();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Intent intent;

                    if ( user == null ) intent = new Intent(SplashScreen.this, LogIn.class);
                    else                intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    finish();
                } catch (InterruptedException e ){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}

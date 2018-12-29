package com.example.kar.horoscope_world;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

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

                    if ( auth.getCurrentUser() == null ) {
                        Intent intent = new Intent(SplashScreen.this, LogIn.class);
                        startActivity(intent);
                    }

                    else {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } catch (InterruptedException e ){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}

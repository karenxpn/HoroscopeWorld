package com.example.kar.horoscope_world;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent( SplashScreen.this, LogIn.class );
                    startActivity ( intent );
                    finish();
                } catch (InterruptedException e ){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}

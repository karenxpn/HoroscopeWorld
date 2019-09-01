package com.kar.horoscope.world.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kar.horoscope.world.R
import com.kar.horoscope.world.repository.SharedPrefPreferenceService
import com.kar.horoscope.world.view.activities.Forecast
import com.kar.horoscope.world.view.activities.MainActivity


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        val preference = getSharedPreferences("Preference", Context.MODE_PRIVATE)
        val sharedPrefPreferenceService = SharedPrefPreferenceService(preference)


        val id = sharedPrefPreferenceService.getPreference("Name", -1)
        val intent: Intent


        if (id != -1) {
            intent = Intent(this, Forecast::class.java)
            intent.putExtra("Title", id)
        }

        else if ( user == null )    intent = Intent ( this, LogIn::class.java )
        else                        intent = Intent ( this, MainActivity::class.java)

        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}
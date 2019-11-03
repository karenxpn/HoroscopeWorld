package com.kar.horoscope.world.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kar.horoscope.world.R
import com.kar.horoscope.world.repository.SharedPrefPreferenceService
import com.kar.horoscope.world.view.activities.Forecast
import com.kar.horoscope.world.view.activities.MainActivity
import java.util.*


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val deviceLanguage = Locale.getDefault().displayLanguage
        if ( deviceLanguage == "ru" ) {
            val config = resources.configuration
            val locale = Locale(deviceLanguage)
            Locale.setDefault(locale)
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)
        }



        val user = FirebaseAuth.getInstance().currentUser

        val preference = getSharedPreferences("Preference", Context.MODE_PRIVATE)
        val sharedPrefPreferenceService = SharedPrefPreferenceService(preference)


        val id = sharedPrefPreferenceService.getPreference("Name", -1)
        val intent: Intent


        when {
            id != -1 -> {
                intent = Intent(this, Forecast::class.java)
                intent.putExtra("Title", id)
            }
            user == null -> intent = Intent ( this, LogIn::class.java )
            else -> intent = Intent ( this, MainActivity::class.java)
        }

        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}
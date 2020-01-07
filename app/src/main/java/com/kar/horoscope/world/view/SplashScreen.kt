package com.kar.horoscope.world.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.kar.horoscope.world.R
import com.kar.horoscope.world.repository.FirebaseRepository
import com.kar.horoscope.world.repository.SharedPrefPreferenceService
import com.kar.horoscope.world.view.activities.Forecast
import com.kar.horoscope.world.view.activities.MainActivity
import com.kar.horoscope.world.viewmodels.forecast.ForecastVMFactory
import com.kar.horoscope.world.viewmodels.forecast.ForecastViewModel
import java.util.*
import kotlin.collections.ArrayList


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
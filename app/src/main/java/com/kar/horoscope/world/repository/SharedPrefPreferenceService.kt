package com.kar.horoscope.world.repository

import android.content.SharedPreferences
import com.kar.horoscope.world.models.Preference
import com.kar.horoscope.world.service.PreferenceService


class SharedPrefPreferenceService( private val storage : SharedPreferences ) : PreferenceService {

    override fun savePreference(preference: Preference)  {
        val editor= storage.edit()
        editor.putInt( preference.tag, preference.preference )
        editor.apply()
    }

    override fun getPreference(tag: String, defaultValue: Int ): Int {
        return storage.getInt( tag, defaultValue )
    }
}
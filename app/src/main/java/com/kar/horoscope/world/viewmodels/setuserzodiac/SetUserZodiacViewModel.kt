package com.kar.horoscope.world.viewmodels.setuserzodiac

import androidx.lifecycle.ViewModel
import com.kar.horoscope.world.models.Preference
import com.kar.horoscope.world.service.PreferenceService

class SetUserZodiacViewModel (private val preferenceService: PreferenceService) : ViewModel() {

    fun saveTheValue( preference: Preference ) {
        preferenceService.savePreference( preference )
    }

    fun getTheValue( tag: String, defaultValue: Int ) : Int {
        return preferenceService.getPreference( tag, defaultValue )
    }
}
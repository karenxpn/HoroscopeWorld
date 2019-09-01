package com.kar.horoscope.world.service

import com.kar.horoscope.world.models.Preference

interface PreferenceService {
    fun savePreference( preference: Preference )
    fun getPreference( tag: String, defaultValue: Int ) : Int
}
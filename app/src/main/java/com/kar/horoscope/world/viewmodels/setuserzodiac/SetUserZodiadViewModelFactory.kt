package com.kar.horoscope.world.viewmodels.setuserzodiac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kar.horoscope.world.service.PreferenceService

class SetUserZodiacViewModelFactory(private val preferenceService: PreferenceService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetUserZodiacViewModel::class.java)) {
            return SetUserZodiacViewModel(
                preferenceService
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
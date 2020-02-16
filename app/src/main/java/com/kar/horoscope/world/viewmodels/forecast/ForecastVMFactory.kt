package com.kar.horoscope.world.viewmodels.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kar.horoscope.world.service.DataService

class ForecastVMFactory(private val dataService: DataService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(dataService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
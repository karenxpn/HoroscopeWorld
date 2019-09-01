package com.kar.horoscope.world.viewmodels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kar.horoscope.world.service.MainService

class ViewModelFactory(private val mainService: MainService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
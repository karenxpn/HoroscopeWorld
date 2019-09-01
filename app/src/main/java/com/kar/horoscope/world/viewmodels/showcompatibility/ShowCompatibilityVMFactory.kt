package com.kar.horoscope.world.viewmodels.showcompatibility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kar.horoscope.world.service.ShowService

class ShowCompatibilityVMFactory (private val showService: ShowService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowCompatibilityViewModel::class.java)) {
            return ShowCompatibilityViewModel(showService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
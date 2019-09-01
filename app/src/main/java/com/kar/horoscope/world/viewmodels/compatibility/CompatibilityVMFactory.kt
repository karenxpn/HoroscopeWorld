package com.kar.horoscope.world.viewmodels.compatibility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kar.horoscope.world.service.CompatibilityService

class CompatibilityVMFactory( private val compatibilityService: CompatibilityService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if( modelClass.isAssignableFrom(CompatibilityViewModel::class.java)) {
            return CompatibilityViewModel( compatibilityService ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
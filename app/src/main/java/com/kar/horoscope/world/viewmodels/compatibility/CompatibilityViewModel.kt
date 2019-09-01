package com.kar.horoscope.world.viewmodels.compatibility

import androidx.lifecycle.ViewModel
import com.kar.horoscope.world.service.CompatibilityService

class CompatibilityViewModel( compatibilityService: CompatibilityService) : ViewModel() {

    val getImageData = compatibilityService.getImages()
    val getNameData = compatibilityService.getNames()
}
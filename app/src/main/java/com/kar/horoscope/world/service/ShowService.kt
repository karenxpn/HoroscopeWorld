package com.kar.horoscope.world.service

import com.kar.horoscope.world.models.CompatibilityModel
import io.reactivex.Observable

interface ShowService {
    fun getCompatibilityData( pair: String ) : Observable<CompatibilityModel>
}
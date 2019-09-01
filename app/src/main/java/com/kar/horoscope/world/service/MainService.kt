package com.kar.horoscope.world.service

import com.kar.horoscope.world.models.MainViewZodiacModel
import io.reactivex.Observable

interface MainService {
    fun generateNames() : List<String>
    fun generateImages() : List<Int>
    fun generateModel() : Observable<List<MainViewZodiacModel>>
    fun sendEmail()
    fun sendFeedback()
    fun goToAbout()
    fun goToSetDefault()
    fun goToCompatibility()
    fun logOut()
}
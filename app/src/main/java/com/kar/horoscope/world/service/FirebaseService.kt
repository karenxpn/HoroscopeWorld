package com.kar.horoscope.world.service

import com.kar.horoscope.world.models.DayModel
import io.reactivex.Observable

interface FirebaseService {
    fun getData( date: String, titleZodiac: String, path: String ) : Observable <DayModel>
    fun pushDailyData( model : DayModel )
    fun getToday() : String
    fun getYesterday() : String
    fun getTomorrow() : String
    fun getMonth() : String
}
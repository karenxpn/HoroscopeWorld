package com.kar.horoscope.world.service

import com.kar.horoscope.world.models.DayModel
import io.reactivex.Observable

interface DataService {
    fun getData( date: String, titleZodiac: String ) : Observable <DayModel>
    fun pushDailyData( model : DayModel )
    fun getToday() : String
    fun getYesterday() : String
    fun getTomorrow() : String
    fun getMonth() : String
}
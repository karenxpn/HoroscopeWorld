package com.kar.horoscope.world.viewmodels.forecast

import androidx.lifecycle.ViewModel
import com.kar.horoscope.world.models.DayModel
import com.kar.horoscope.world.service.FirebaseService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel( private val firebaseService: FirebaseService) : ViewModel() {

    fun getFirebaseData(title: String, pageNumber: Int? ): Observable<DayModel> {
        when (pageNumber) {
            1 -> return firebaseService
                .getData(firebaseService.getYesterday(), title, "date" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            2 -> return firebaseService
                .getData(firebaseService.getToday(), title, "date" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            3 -> return firebaseService
                .getData(firebaseService.getTomorrow(), title, "date" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            4 -> return firebaseService
                .getData(firebaseService.getMonth(), title, "month" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

        return Observable.just(DayModel("00/00/00", "Download Failed" ))
    }
}
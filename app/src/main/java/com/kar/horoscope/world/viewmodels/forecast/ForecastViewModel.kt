package com.kar.horoscope.world.viewmodels.forecast

import androidx.lifecycle.ViewModel
import com.kar.horoscope.world.models.DayModel
import com.kar.horoscope.world.service.DataService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel( private val dataService: DataService) : ViewModel() {

    fun getFirebaseData(title: String, pageNumber: Int? ): Observable<DayModel> {
        when (pageNumber) {
            1 -> return dataService
                .getData(dataService.getYesterday(), title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            2 -> return dataService
                .getData(dataService.getToday(), title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            3 -> return dataService
                .getData(dataService.getTomorrow(), title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            4 -> return dataService
                .getData(dataService.getMonth(), title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

        return Observable.just(DayModel("00/00/00", "Download Failed" ))
    }
}
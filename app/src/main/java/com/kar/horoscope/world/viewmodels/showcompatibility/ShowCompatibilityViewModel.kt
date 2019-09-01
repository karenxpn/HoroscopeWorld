package com.kar.horoscope.world.viewmodels.showcompatibility

import androidx.lifecycle.ViewModel
import com.kar.horoscope.world.models.CompatibilityModel
import com.kar.horoscope.world.service.ShowService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShowCompatibilityViewModel(private val showService: ShowService) : ViewModel() {

    fun getText( pair: String ) : Observable<CompatibilityModel> {
        return showService.getCompatibilityData( pair )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
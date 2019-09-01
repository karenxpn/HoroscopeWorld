package com.kar.horoscope.world.viewmodels.main

import androidx.lifecycle.ViewModel
import com.kar.horoscope.world.R
import com.kar.horoscope.world.service.MainService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(mainService: MainService ) : ViewModel() {

    val getObservableListModel =
        mainService
            .generateModel()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() )!!

    private val service = mainService

    fun navigationLogic( id : Int ) {
        if ( id == R.id.write )         service.sendEmail()
        if ( id == R.id.feedback )      service.sendFeedback()
        if ( id == R.id.about )         service.goToAbout()
        if ( id == R.id.favourite )     service.goToSetDefault()
        if ( id == R.id.compatibility ) service.goToCompatibility()
        if ( id == R.id.exit )          service.logOut()
    }

}
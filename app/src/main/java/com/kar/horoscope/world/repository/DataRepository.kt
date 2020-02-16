package com.kar.horoscope.world.repository

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.kar.horoscope.world.models.DayModel
import com.kar.horoscope.world.service.DataService
import io.reactivex.Observable
import org.jsoup.Jsoup
import java.lang.Exception
import java.time.LocalDate
import java.util.*

@Suppress("UNREACHABLE_CODE")
class DataRepository : DataService {

    override fun getData ( date: String, titleZodiac: String): Observable<DayModel> {

        return Observable.create {

            try {
                val doc = Jsoup.connect("https://www.vogue.in/horoscope/product/${titleZodiac.toLowerCase()}-horoscope-today-$date/").get()

                for ( row in doc.select("div.stroy-wrapper p") ) {
                    it.onNext(DayModel(date, row.text()) )
                    break
                }
            } catch (e: Exception ) {
                it.onNext(DayModel(null, "Please check again later"))
            }
        }
    }

    override fun pushDailyData(model: DayModel) {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun getToday(): String {
        val calendar = LocalDate.now()

        return "${calendar.month}-${calendar.dayOfMonth}-${calendar.year}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun getYesterday(): String {
        var calendar = LocalDate.now()
        calendar = calendar.minusDays(1)

        return "${calendar.month}-${calendar.dayOfMonth}-${calendar.year}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun getTomorrow(): String {

        var calendar = LocalDate.now()
        calendar = calendar.plusDays(1)

        return "${calendar.month}-${calendar.dayOfMonth}-${calendar.year}"

    }

    override fun getMonth(): String {
        val calendar = Calendar.getInstance()
        return ( calendar.get( Calendar.MONTH ) + 1 ).toString()
    }

}
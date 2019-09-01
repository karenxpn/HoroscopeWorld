package com.kar.horoscope.world.repository

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore
import com.kar.horoscope.world.models.DayModel
import com.kar.horoscope.world.service.FirebaseService
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNREACHABLE_CODE")
class FirebaseRepository : FirebaseService {

    private val ref = FirebaseFirestore.getInstance()

    override fun getData ( date: String, titleZodiac: String, path: String ): Observable<DayModel> {

        val collectionReference = ref.collection(titleZodiac)
        val query: com.google.firebase.firestore.Query = collectionReference.whereEqualTo(path, date )

        return Observable.create {

            query.get().addOnCompleteListener { task ->
                if ( task.isSuccessful ) {
                    for ( snapshot in task.result!! ) {
                        val cur = snapshot.toObject(DayModel::class.java)
                        it.onNext(cur)
                    }
                }
            }
        }
    }

    override fun pushDailyData(model: DayModel) {
    }

    @SuppressLint("SimpleDateFormat")
    override fun getToday(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat ( "dd/MM/yyyy" )

        return dateFormat.format( calendar.time )
    }

    @SuppressLint("SimpleDateFormat")
    override fun getYesterday(): String {
        val calendar = Calendar.getInstance()
        calendar.add ( Calendar.DATE, -1 )

        val dateFormat = SimpleDateFormat ( "dd/MM/yyyy" )

        return dateFormat.format( calendar.time )

    }

    @SuppressLint("SimpleDateFormat")
    override fun getTomorrow(): String {

        val calendar = Calendar.getInstance()
        calendar.add ( Calendar.DATE, +1 )

        val dateFormat = SimpleDateFormat ( "dd/MM/yyyy" )

        return dateFormat.format( calendar.time )
    }

    override fun getMonth(): String {
        val calendar = Calendar.getInstance()
        return ( calendar.get( Calendar.MONTH ) + 1 ).toString()
    }
}
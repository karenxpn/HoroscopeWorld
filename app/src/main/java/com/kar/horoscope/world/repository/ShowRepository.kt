package com.kar.horoscope.world.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kar.horoscope.world.models.CompatibilityModel
import com.kar.horoscope.world.service.ShowService
import io.reactivex.Observable

class ShowRepository : ShowService {
    override fun getCompatibilityData( pair: String ): Observable<CompatibilityModel> {
        val ref = FirebaseFirestore.getInstance()
        val collectionReference = ref.collection("compatibility")
        val query: com.google.firebase.firestore.Query = collectionReference.whereEqualTo("pair", pair )

        return Observable.create {
            query.get().addOnCompleteListener { task->
                if ( task.isSuccessful ) {
                    for ( snapshot in task.result!! ) {
                        val cur = snapshot.toObject(CompatibilityModel::class.java)
                        it.onNext(cur)
                    }
                }
            }
        }

    }
}
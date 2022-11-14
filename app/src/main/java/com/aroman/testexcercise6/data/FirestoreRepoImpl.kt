package com.aroman.testexcercise6.data

import android.util.Log
import com.aroman.testexcercise6.domain.HeartRepo
import com.aroman.testexcercise6.domain.entity.HeartData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import io.reactivex.Observable

class FirestoreRepoImpl : HeartRepo {
    private val db = Firebase.firestore

    override fun getHeartList(): Observable<List<HeartData>> {
        return Observable.create { emitter ->
            db.collection(HEART_DATA)
                .get()
                .addOnSuccessListener { result ->
                    val temp = result.toObjects(HeartData::class.java)
                    emitter.onNext(temp)
                }
                .addOnFailureListener { e -> emitter.onError(e) }
        }
    }

    override fun saveHeartData(heartData: HeartData): Completable {
        return Completable.create { emitter ->
            db.collection(HEART_DATA)
                .add(heartData)
                .addOnSuccessListener {
                    Log.d("@@@", "complete: " + it.toString())
                    emitter.onComplete()
                }
                .addOnFailureListener { e ->
                    Log.d("@@@", "failure: " + e.toString())
                    emitter.onError(e)
                }
        }
    }

    companion object {
        private const val HEART_DATA = "heartData"
    }
}
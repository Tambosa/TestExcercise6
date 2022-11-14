package com.aroman.testexcercise6.data

import com.aroman.testexcercise6.domain.HeartRepo
import com.aroman.testexcercise6.domain.entity.HeartData
import io.reactivex.Completable
import io.reactivex.Observable

class FakeRepoImpl : HeartRepo {
    override fun getHeartList(): Observable<List<HeartData>> {
        return Observable.just(
            listOf(
                HeartData(0, 1668421033371, 137, 71, 59),
                HeartData(0, 1668421033371, 126, 67, 49),
                HeartData(0, 1668421033371, 141, 64, 63),
                HeartData(0, 1668421033371, 127, 73, 58),
                HeartData(0, 1668421033371, 150, 83, 55),
                HeartData(0, 1668421033371, 129, 79, 53),
            )
        )
    }

    override fun saveHeartData(heartData: HeartData): Completable {
        return Completable.complete()
    }
}
package com.aroman.testexcercise6.data

import com.aroman.testexcercise6.domain.HeartRepo
import com.aroman.testexcercise6.domain.entity.HeartData
import io.reactivex.Completable
import io.reactivex.Observable

class FakeRepoImpl : HeartRepo {
    private val fakeList = mutableListOf<HeartData>(
        HeartData(1668421033371, 137, 71, 59),
        HeartData(1668021033371, 126, 67, 49),
        HeartData(1668011033371, 141, 64, 63),
        HeartData(1666421033371, 127, 73, 58),
        HeartData(1666411033371, 150, 83, 55),
        HeartData(1664421033371, 129, 79, 53),
    )

    override fun getHeartList(): Observable<List<HeartData>> {
        return Observable.just(
            fakeList
        )
    }

    override fun saveHeartData(heartData: HeartData): Completable {
        fakeList.add(heartData)
        return Completable.complete()
    }

    override fun deleteHeartData(heartData: HeartData): Completable {
        fakeList.remove(heartData)
        return Completable.complete()
    }
}
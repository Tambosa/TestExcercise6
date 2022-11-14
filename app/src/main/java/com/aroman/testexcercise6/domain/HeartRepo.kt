package com.aroman.testexcercise6.domain

import com.aroman.testexcercise6.domain.entity.HeartData
import io.reactivex.Completable
import io.reactivex.Observable

interface HeartRepo {
    fun getHeartList(): Observable<List<HeartData>>
    fun saveHeartData(heartData: HeartData): Completable
}
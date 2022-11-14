package com.aroman.testexcercise6.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aroman.testexcercise6.domain.HeartRepo
import com.aroman.testexcercise6.domain.entity.HeartData
import com.aroman.testexcercise6.utils.applySchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val repo: HeartRepo) : ViewModel() {
    private val _liveData: MutableLiveData<List<HeartData>> = MutableLiveData()
    val heartDataList: LiveData<List<HeartData>> = _liveData
    private val compositeDisposable = CompositeDisposable()

    fun loadHeartList() {
        repo.getHeartList()
            .applySchedulers()
            .subscribe({ result ->
                _liveData.postValue(result)
            }, {})
            .let { compositeDisposable.add(it) }
    }

    fun saveHeartData(heartData: HeartData) {
        repo.saveHeartData(heartData)
            .applySchedulers()
            .subscribe({
                Log.d("@@@", heartData.toString())
            }, {})
            .let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}
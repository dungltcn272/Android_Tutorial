package com.example.rxretrofitmvvmpartern

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.example.rxretrofitmvvmpartern.api.ApiService
import com.example.rxretrofitmvvmpartern.model.ObjectData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel {

    private lateinit var mDisposable: Disposable
    var list = ObservableArrayList<ObjectData>()
    var isSuccess = ObservableBoolean()
    fun callApi(){
        ApiService.apiService.callApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<ObjectData>> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable=d
                }

                override fun onNext(t: List<ObjectData>) {
                    list.addAll(t)
                }

                override fun onError(e: Throwable) {
                    isSuccess.set(false)
                }

                override fun onComplete() {
                    isSuccess.set(true)
                }
            })

    }
}
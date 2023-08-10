package com.example.rxandroidandretrofit

import com.example.rxandroidandretrofit.api.ApiService
import com.example.rxandroidandretrofit.model.ObjectData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter(val mMainView: MainView) {
    private  var mListData: List<ObjectData> = ArrayList()
    private lateinit var mDisposable: Disposable
    fun getDisposable(): Disposable {
        return mDisposable
    }
    fun login(){
        ApiService.apiService.callApi()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<ObjectData>> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable=d
                }

                override fun onNext(t: List<ObjectData>) {
                    mListData= t as ArrayList<ObjectData>
                }

                override fun onError(e: Throwable) {
                    mMainView.loginError()
                }

                override fun onComplete() {
                    mMainView.loginSuccess(mListData)
                }
            })

    }
}
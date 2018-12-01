package com.bestapplication.tikiandroidtest.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bestapplication.tikiandroidtest.Response
import com.bestapplication.tikiandroidtest.extendsion.toTwoLines
import com.bestapplication.tikiandroidtest.model.KeywordTwoLine
import com.bestapplication.tikiandroidtest.network.HotKeywordAPI
import com.bestapplication.tikiandroidtest.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val response = MutableLiveData<Response<List<KeywordTwoLine>>>()
    private val retrofit = RetrofitClient.getClient()
    private val service = retrofit.create(HotKeywordAPI::class.java)

    fun dataLive(): LiveData<Response<List<KeywordTwoLine>>> = response

    init {
        fetchHotKeyWords()
    }

    private fun fetchHotKeyWords() {
        compositeDisposable.add(
            service.getHotKeywords()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { response.value = Response.loading() }
                .subscribe({ keywords ->
                    response.value = Response.succeed(
                        keywords.asSequence()
                            .filter { !it.isEmpty() }
                            .map { it.toTwoLines() }
                            .toList()
                    )
                }, { t: Throwable ->
                    response.value = Response.error(t)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

package com.zia.emptyclassroom.model

import API
import com.zia.emptyclassroom.bean.Empty
import com.zia.emptyclassroom.bean.NowTime
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by zia on 2018/3/7.
 */
class InternetModel {

    private val myService: MyService = Retrofit
            .Builder()
            .baseUrl(API.BaseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyService::class.java)

    fun getTime(): Observable<NowTime> {
        return myService.getTime()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getEmpty(week: Int, day: Int, start: Int, end: Int): Observable<Empty> {
        val map = HashMap<String, String>()
        map["week"] = week.toString()
        map["day"] = day.toString()
        map["start"] = start.toString()
        map["end"] = end.toString()
        return myService.getEmpty(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}
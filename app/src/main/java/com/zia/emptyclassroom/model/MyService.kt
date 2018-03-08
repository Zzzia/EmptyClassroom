package com.zia.emptyclassroom.model

import com.zia.emptyclassroom.bean.Empty
import com.zia.emptyclassroom.bean.NowTime
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * Created by zia on 2018/3/7.
 */
interface MyService {

    @GET("empty/")
    fun getTime(): Observable<NowTime>


    @POST("empty/")
    fun getEmpty(@QueryMap map: Map<String, String>): Observable<Empty>
}
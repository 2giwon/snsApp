package com.egiwon.snsapp.data

import com.egiwon.snsapp.data.entity.HomeContentResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ContentService {

    @GET("home")
    fun getHomeContent(): Single<HomeContentResponse>
}
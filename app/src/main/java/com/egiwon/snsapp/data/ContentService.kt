package com.egiwon.snsapp.data

import com.egiwon.snsapp.data.entity.CardContentResponse
import com.egiwon.snsapp.data.entity.HomeContentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentService {

    @GET("home")
    fun getHomeContent(): Single<HomeContentResponse>

    @GET("cards")
    fun getImageFeed(
        @Query("page") page: Int,
        @Query("per") perPage: Int
    ): Single<CardContentResponse>
}
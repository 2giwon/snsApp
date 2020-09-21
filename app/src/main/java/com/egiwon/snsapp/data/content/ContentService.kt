package com.egiwon.snsapp.data.content

import com.egiwon.snsapp.data.entity.CardContentResponse
import com.egiwon.snsapp.data.entity.CardDetailResponse
import com.egiwon.snsapp.data.entity.HomeContentResponse
import com.egiwon.snsapp.data.entity.UserDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {

    @GET("home")
    fun getHomeContent(): Single<HomeContentResponse>

    @GET("cards")
    fun getImageFeed(
        @Query("page") page: Int,
        @Query("per") perPage: Int
    ): Single<CardContentResponse>

    @GET("users/{id}")
    fun getUserDetail(@Path("id") id: String): Single<UserDetailResponse>

    @GET("cards/{id}")
    fun getCardDetail(@Path("id") id: String): Single<CardDetailResponse>
}
package com.egiwon.snsapp.data.content

import com.egiwon.snsapp.data.entity.CardContentResponse
import com.egiwon.snsapp.data.entity.HomeContentResponse
import io.reactivex.Single

interface ContentDataSource {

    fun getHomeContents(): Single<HomeContentResponse>

    fun getImageFeed(page: Int, perPage: Int): Single<CardContentResponse>
}
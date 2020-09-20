package com.egiwon.snsapp.data.source

import com.egiwon.snsapp.data.ContentDataSource
import com.egiwon.snsapp.data.ContentService
import com.egiwon.snsapp.data.entity.CardContentResponse
import com.egiwon.snsapp.data.entity.HomeContentResponse
import io.reactivex.Single
import javax.inject.Inject

class ContentRemoteDataSource @Inject constructor(
    private val contentService: ContentService
) : ContentDataSource {

    override fun getHomeContents(): Single<HomeContentResponse> =
        contentService.getHomeContent()
            .onErrorReturn {
                HomeContentResponse(
                    ok = false,
                    popularCards = emptyList(),
                    popularUsers = emptyList()
                )
            }

    override fun getImageFeed(page: Int, perPage: Int): Single<CardContentResponse> =
        contentService.getImageFeed(page, perPage)

}
package com.egiwon.snsapp.data.source

import com.egiwon.snsapp.data.content.ContentDataSource
import com.egiwon.snsapp.data.content.ContentService
import com.egiwon.snsapp.data.entity.CardContentResponse
import com.egiwon.snsapp.data.entity.CardDetailResponse
import com.egiwon.snsapp.data.entity.HomeContentResponse
import com.egiwon.snsapp.data.entity.UserDetailResponse
import io.reactivex.Single
import javax.inject.Inject

class ContentRemoteDataSource @Inject constructor(
    private val contentService: ContentService
) : ContentDataSource {

    override fun getHomeContents(): Single<HomeContentResponse> =
        contentService.getHomeContent()

    override fun getImageFeed(page: Int, perPage: Int): Single<CardContentResponse> =
        contentService.getImageFeed(page, perPage)

    override fun getUserDetailInfo(id: Int): Single<UserDetailResponse> =
        contentService.getUserDetail(id.toString())

    override fun getCardDetailInfo(id: Int): Single<CardDetailResponse> =
        contentService.getCardDetail(id.toString())


}
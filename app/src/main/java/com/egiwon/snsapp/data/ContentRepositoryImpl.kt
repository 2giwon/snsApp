package com.egiwon.snsapp.data

import com.egiwon.snsapp.data.entity.HomeContentResponse
import io.reactivex.Single
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override fun getHomeContent(): Single<HomeContentResponse> = contentDataSource.getHomeContents()
}
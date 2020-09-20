package com.egiwon.snsapp.data

import com.egiwon.snsapp.data.entity.HomeContentResponse
import io.reactivex.Single

interface ContentRepository {

    fun getHomeContent(): Single<HomeContentResponse>
}
package com.egiwon.snsapp.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.egiwon.snsapp.data.entity.CardDetailResponse
import com.egiwon.snsapp.data.entity.HomeContentResponse
import com.egiwon.snsapp.data.entity.UserDetailResponse
import com.egiwon.snsapp.tab.imagefeed.model.Card
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface ContentRepository {

    fun getHomeContent(): Single<HomeContentResponse>

    fun getImageFeed(
        compositeDisposable: CompositeDisposable,
        onStart: () -> Unit,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ): LiveData<PagedList<Card>>

    fun getUserDetailInfo(id: Int): Single<UserDetailResponse>

    fun getCardDetailInfo(id: Int): Single<CardDetailResponse>
}
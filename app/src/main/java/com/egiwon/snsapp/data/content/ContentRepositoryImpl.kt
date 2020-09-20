package com.egiwon.snsapp.data.content

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.data.entity.HomeContentResponse
import com.egiwon.snsapp.data.entity.UserDetailResponse
import com.egiwon.snsapp.data.paging.CardDataSourceFactory
import com.egiwon.snsapp.tab.imagefeed.model.Card
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override fun getHomeContent(): Single<HomeContentResponse> = contentDataSource.getHomeContents()

    override fun getImageFeed(
        compositeDisposable: CompositeDisposable,
        onStart: () -> Unit,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ): LiveData<PagedList<Card>> {
        val cardDataSourceFactory = CardDataSourceFactory(
            compositeDisposable,
            contentDataSource,
            onStart,
            onSuccess,
            onFailure
        )

        return LivePagedListBuilder(cardDataSourceFactory, CardDataSourceFactory.pageConfig)
            .setFetchExecutor {
                Completable
                    .fromRunnable(it)
                    .subscribeOn(Schedulers.single())
                    .subscribe()
                    .addTo(compositeDisposable)
            }
            .build()
    }

    override fun getUserDetailInfo(id: Int): Single<UserDetailResponse> =
        contentDataSource.getUserDetailInfo(id)


}
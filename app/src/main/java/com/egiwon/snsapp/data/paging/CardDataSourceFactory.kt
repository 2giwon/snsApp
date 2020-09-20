package com.egiwon.snsapp.data.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.egiwon.snsapp.data.content.ContentDataSource
import com.egiwon.snsapp.data.entity.CardItem
import com.egiwon.snsapp.tab.imagefeed.model.Card
import com.egiwon.snsapp.tab.imagefeed.model.mapToCard
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CardDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val remoteDataSource: ContentDataSource,
    private val onStart: () -> Unit,
    private val onSuccess: () -> Unit,
    private val onFailure: (Throwable) -> Unit
) : DataSource.Factory<Int, Card>() {

    override fun create(): DataSource<Int, Card> {
        return CardPagingDataSource(compositeDisposable, remoteDataSource)
    }

    inner class CardPagingDataSource(
        private val compositeDisposable: CompositeDisposable,
        private val remoteDataSource: ContentDataSource
    ) : PageKeyedDataSource<Int, Card>() {

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Card>
        ) {
            onStart()

            remoteDataSource.getImageFeed(START_PAGE, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        if (!it.ok) onFailure(Throwable(it.msg))
                        val result = it.cards.map(CardItem::mapToCard)
                        callback.onResult(result, 0, START_PAGE + 1)
                        onSuccess()
                    },
                    onError = {
                        onFailure(it)
                    }
                ).addTo(compositeDisposable)
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Card>
        ) {
            remoteDataSource.getImageFeed(params.key + 1, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        if (!it.ok) onFailure(Throwable(it.msg))
                        val result = it.cards.map(CardItem::mapToCard)
                        callback.onResult(result, params.key + 1)
                        onSuccess()
                    },
                    onError = {
                        onFailure(it)
                    }
                ).addTo(compositeDisposable)
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Card>
        ) = Unit
    }

    companion object {
        private const val START_PAGE = 1
        private const val PAGE_SIZE = 20

        val pageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE)
            .build()
    }
}
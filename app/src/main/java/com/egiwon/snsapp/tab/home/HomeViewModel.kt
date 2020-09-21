package com.egiwon.snsapp.tab.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseViewModel
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.data.entity.CardItem
import com.egiwon.snsapp.data.entity.UserItem
import com.egiwon.snsapp.tab.home.model.HomeContent
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.home.model.mapToUser
import com.egiwon.snsapp.tab.imagefeed.model.Card
import com.egiwon.snsapp.tab.imagefeed.model.mapToCard
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeViewModel @ViewModelInject constructor(
    private val repository: ContentRepository
) : BaseViewModel() {

    private val _cardContents = MutableLiveData<List<Card>>()
    val cardContents: LiveData<List<Card>> get() = _cardContents

    private val _userContents = MutableLiveData<List<User>>()
    val userContents: LiveData<List<User>> get() = _userContents

    fun getHomeContents() {
        repository.getHomeContent()
            .subscribeOn(Schedulers.io())
            .map {
                HomeContent(
                    it.popularCards.map(CardItem::mapToCard),
                    it.popularUsers.map(UserItem::mapToUser)
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _cardContents.value = it.popularCards
                    _userContents.value = it.popularUsers
                },
                onError = {
                    toastMessageMutableLiveData.value = R.string.error_load_home_content
                }
            ).addTo(compositeDisposable)
    }
}
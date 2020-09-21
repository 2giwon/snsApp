package com.egiwon.snsapp.detail.card

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseViewModel
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.detail.model.mapToCardDetail
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.imagefeed.model.Card
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CardDetailViewModel @ViewModelInject constructor(
    private val repository: ContentRepository
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> get() = _card

    private val _recommendCards = MutableLiveData<List<Card>>()
    val recommendCards: LiveData<List<Card>> get() = _recommendCards

    fun getCardDetailInfo(id: Int) {
        repository.getCardDetailInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val cardDetail = it.mapToCardDetail()
                    _user.value = cardDetail.user
                    _card.value = cardDetail.card
                    _recommendCards.value = cardDetail.recommendCards
                },
                onError = {
                    toastMessageMutableLiveData.value = R.string.error_load_card_detail
                }
            ).addTo(compositeDisposable)
    }
}
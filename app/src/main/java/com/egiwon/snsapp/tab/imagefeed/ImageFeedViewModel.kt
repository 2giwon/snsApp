package com.egiwon.snsapp.tab.imagefeed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseViewModel
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.tab.imagefeed.model.Card

class ImageFeedViewModel @ViewModelInject constructor(
    private val repository: ContentRepository
) : BaseViewModel() {

    private val _loadingBar = MutableLiveData<Boolean>()
    val loadingBar: LiveData<Boolean> get() = _loadingBar

    lateinit var resultCardList: LiveData<PagedList<Card>>

    fun getImageFeed() {
        resultCardList = repository.getImageFeed(
            compositeDisposable,
            onStart = {
                _loadingBar.postValue(true)
            },
            onSuccess = {
                _loadingBar.postValue(false)
            },
            onFailure = {
                toastMessageMutableLiveData.postValue(R.string.error_load_card)
                _loadingBar.postValue(false)
            }
        )
    }
}
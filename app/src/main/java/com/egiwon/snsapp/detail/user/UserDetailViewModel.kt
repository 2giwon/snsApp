package com.egiwon.snsapp.detail.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.snsapp.base.BaseViewModel
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.home.model.mapToUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserDetailViewModel @ViewModelInject constructor(
    private val repository: ContentRepository
) : BaseViewModel() {

    private val _userDetailInfo = MutableLiveData<User>()
    val userDetailInfo: LiveData<User> get() = _userDetailInfo

    fun getUserDetailInfo(id: Int) {
        repository.getUserDetailInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                _userDetailInfo.value = it.user.mapToUser()
            }.addTo(compositeDisposable)
    }
}
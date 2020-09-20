package com.egiwon.snsapp.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.snsapp.base.BaseViewModel

class AuthViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin

    fun setIsLogin(isLogin: Boolean) {
        _isLogin.value = isLogin
    }
}
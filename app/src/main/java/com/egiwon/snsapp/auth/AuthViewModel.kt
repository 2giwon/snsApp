package com.egiwon.snsapp.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.snsapp.Event
import com.egiwon.snsapp.R
import com.egiwon.snsapp.auth.model.UserAuth
import com.egiwon.snsapp.auth.model.mapToUserAuth
import com.egiwon.snsapp.base.BaseViewModel
import com.egiwon.snsapp.data.auth.AuthRepository
import com.egiwon.snsapp.data.auth.model.AuthRequestData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AuthViewModel @ViewModelInject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin

    private val _isInvalidNickName = MutableLiveData<Event<Unit>>()
    val isInvalidNickName: LiveData<Event<Unit>> get() = _isInvalidNickName

    private val _isInvalidIntroduction = MutableLiveData<Event<Unit>>()
    val isInvalidIntroduction: LiveData<Event<Unit>> get() = _isInvalidIntroduction

    private val _isInvalidPassword = MutableLiveData<Event<Unit>>()
    val isInvalidPassword: LiveData<Event<Unit>> get() = _isInvalidPassword

    private val _userAuth = MutableLiveData<UserAuth>()
    val userAuth: LiveData<UserAuth> get() = _userAuth

    private val _errorToastMessage = MutableLiveData<Event<String>>()
    val errorToastMessage: LiveData<Event<String>> get() = _errorToastMessage

    fun setIsLogin(isLogin: Boolean) {
        _isLogin.value = isLogin
    }

    fun requestSignUpOrLogin(nickname: String, password: String, introduction: String) {
        if (_isLogin.value == true) {
            login(nickname, password)
        } else {
            signUp(nickname, introduction, password)
        }
    }

    private fun signUp(nickname: String, introduction: String, password: String) {
        if (isValidNickName(nickname) &&
            isValidIntroduction(introduction) &&
            isValidPassword(password)
        ) {
            repository.signUp(
                AuthRequestData(
                    nickname = nickname, introduction = introduction, pwd = password
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { authResponse ->
                        if (!authResponse.ok) {
                            _errorToastMessage.value = Event(authResponse.error_msg)
                        } else {
                            _userAuth.value = authResponse.mapToUserAuth()
                        }

                    },
                    onError = {
                        toastMessageMutableLiveData.value = R.string.error_join_message
                    }
                ).addTo(compositeDisposable)
        }

    }

    private fun login(nickname: String, password: String) {
        if (isValidNickName(nickname) && isValidPassword(password)) {
            repository.login(AuthRequestData(nickname = nickname, pwd = password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { authResponse ->
                        if (!authResponse.ok) {
                            _errorToastMessage.value = Event(authResponse.error_msg)
                        } else {
                            _userAuth.value = authResponse.mapToUserAuth()
                        }

                    },
                    onError = {
                        toastMessageMutableLiveData.value = R.string.error_login_message
                    }
                ).addTo(compositeDisposable)
        }
    }

    private fun isValidNickName(nickname: String): Boolean {
        if (nickname.isEmpty()) {
            _isInvalidNickName.value = Event(Unit)
            return false
        }

        return true
    }

    private fun isValidIntroduction(introduction: String): Boolean {
        if (introduction.isEmpty()) {
            _isInvalidIntroduction.value = Event(Unit)
            return false
        }

        return true
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.isEmpty()) {
            _isInvalidPassword.value = Event(Unit)
            return false
        }

        return true
    }
}
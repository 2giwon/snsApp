package com.egiwon.snsapp.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.snsapp.Event
import com.egiwon.snsapp.base.BaseViewModel

class MainViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _showLogin = MutableLiveData<Event<Unit>>()
    val showLogin: LiveData<Event<Unit>> get() = _showLogin

    private val _showSignUp = MutableLiveData<Event<Unit>>()
    val showSignUp: LiveData<Event<Unit>> get() = _showSignUp

    fun requestShowLoginFragment() {
        _showLogin.value = Event(Unit)
    }

    fun requestShowSignUpFragment() {
        _showSignUp.value = Event(Unit)
    }
}
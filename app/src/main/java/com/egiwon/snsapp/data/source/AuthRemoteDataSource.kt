package com.egiwon.snsapp.data.source

import com.egiwon.snsapp.data.auth.AuthDataSource
import com.egiwon.snsapp.data.auth.AuthService
import com.egiwon.snsapp.data.auth.model.AuthRequestData
import com.egiwon.snsapp.data.entity.AuthResponse
import io.reactivex.Single
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override fun signUp(authRequestData: AuthRequestData): Single<AuthResponse> =
        authService.requestSignUp(authRequestData)

    override fun login(authRequestData: AuthRequestData): Single<AuthResponse> =
        authService.requestLogin(authRequestData)


}
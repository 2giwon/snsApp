package com.egiwon.snsapp.data.auth

import com.egiwon.snsapp.data.AuthRepository
import com.egiwon.snsapp.data.auth.model.AuthRequestData
import com.egiwon.snsapp.data.entity.AuthResponse
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {

    override fun signUp(authRequestData: AuthRequestData): Single<AuthResponse> =
        dataSource.signUp(authRequestData)

    override fun login(authRequestData: AuthRequestData): Single<AuthResponse> =
        dataSource.login(authRequestData)


}
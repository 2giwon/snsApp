package com.egiwon.snsapp.data

import com.egiwon.snsapp.data.auth.model.AuthRequestData
import com.egiwon.snsapp.data.entity.AuthResponse
import io.reactivex.Single

interface AuthRepository {

    fun signUp(authRequestData: AuthRequestData): Single<AuthResponse>

    fun login(authRequestData: AuthRequestData): Single<AuthResponse>
}
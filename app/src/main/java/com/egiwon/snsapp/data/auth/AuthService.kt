package com.egiwon.snsapp.data.auth

import com.egiwon.snsapp.data.auth.model.AuthRequestData
import com.egiwon.snsapp.data.entity.AuthResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("users")
    fun requestSignUp(@Body authRequestData: AuthRequestData): Single<AuthResponse>

    @POST("users/sign_in")
    fun requestLogin(@Body authRequestData: AuthRequestData): Single<AuthResponse>

}
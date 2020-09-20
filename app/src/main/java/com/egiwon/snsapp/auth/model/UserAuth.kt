package com.egiwon.snsapp.auth.model

import com.egiwon.snsapp.data.entity.AuthResponse

data class UserAuth(
    val userId: Int = -1
)

fun AuthResponse.mapToUserAuth(): UserAuth = UserAuth(userId = userId)
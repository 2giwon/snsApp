package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("user_id")
    val userId: Int = -1,

    @SerializedName("ok")
    val ok: Boolean = false,

    @SerializedName("error_msg")
    val error_msg: String
)
package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("ok")
    val ok: Boolean = false,
    @SerializedName("error_msg")
    val errorMsg: String = "",
    @SerializedName("user")
    val user: UserItem = UserItem()
)
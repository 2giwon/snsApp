package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("nickname")
    val nickname: String = "",
    @SerializedName("introduction")
    val introduction: String = "",
    @SerializedName("id")
    val id: Int = -1
)
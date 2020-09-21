package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class HomeContentResponse(
    @SerializedName("popular_cards")
    val popularCards: List<CardItem> = emptyList(),
    @SerializedName("popular_users")
    val popularUsers: List<UserItem> = emptyList(),
    @SerializedName("ok")
    val ok: Boolean = false,
    @SerializedName("msg")
    val msg: String
)
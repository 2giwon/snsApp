package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class HomeContentResponse(
    @SerializedName("popular_cards")
    val popularCards: List<CardItem>,
    @SerializedName("popular_users")
    val popularUsers: List<UserItem>,
    @SerializedName("ok")
    val ok: Boolean,
    @SerializedName("msg")
    val msg: String
)
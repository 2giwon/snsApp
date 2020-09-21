package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class CardDetailResponse(
    @SerializedName("card")
    val card: CardItem = CardItem(),
    @SerializedName("user")
    val user: UserItem = UserItem(),
    @SerializedName("recommend_cards")
    val recommendCards: List<CardItem> = emptyList(),
    @SerializedName("ok")
    val ok: Boolean = false,
    @SerializedName("msg")
    val msg: String = ""
)
package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class CardContentResponse(
    @SerializedName("ok")
    val ok: Boolean,
    @SerializedName("cards")
    val cards: List<CardItem>
)
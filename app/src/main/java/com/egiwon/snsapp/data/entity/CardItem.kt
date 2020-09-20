package com.egiwon.snsapp.data.entity

import com.google.gson.annotations.SerializedName

data class CardItem(
    @SerializedName("user_id")
    val userId: Int = -1,
    @SerializedName("img_url")
    val imgUrl: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = -1
)
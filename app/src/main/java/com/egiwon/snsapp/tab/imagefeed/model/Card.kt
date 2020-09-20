package com.egiwon.snsapp.tab.imagefeed.model

import com.egiwon.snsapp.data.entity.CardItem

data class Card(
    val userId: Int = -1,
    val imageUrl: String = "",
    val description: String = "",
    val id: Int = -1
)

fun CardItem.mapToCard(): Card = Card(
    userId = userId,
    imageUrl = imgUrl,
    description = description,
    id = id
)


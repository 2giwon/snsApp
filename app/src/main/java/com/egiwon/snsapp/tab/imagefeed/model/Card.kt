package com.egiwon.snsapp.tab.imagefeed.model

import com.egiwon.snsapp.base.BaseIdentifier
import com.egiwon.snsapp.data.entity.CardItem

data class Card(
    val userId: Int = -1,
    val imageUrl: String = "",
    val description: String = "",
    val id: Int = -1
) : BaseIdentifier() {
    override val identifier: Any
        get() = userId
}

fun CardItem.mapToCard(): Card = Card(
    userId = userId,
    imageUrl = imgUrl,
    description = description,
    id = id
)


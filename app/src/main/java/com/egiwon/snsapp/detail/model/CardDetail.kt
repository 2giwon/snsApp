package com.egiwon.snsapp.detail.model

import com.egiwon.snsapp.data.entity.CardDetailResponse
import com.egiwon.snsapp.data.entity.CardItem
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.home.model.mapToUser
import com.egiwon.snsapp.tab.imagefeed.model.Card
import com.egiwon.snsapp.tab.imagefeed.model.mapToCard

data class CardDetail(
    val card: Card = Card(),
    val user: User = User(),
    val recommendCards: List<Card> = emptyList()
)

fun CardDetailResponse.mapToCardDetail(): CardDetail =
    CardDetail(
        card = card.mapToCard(),
        user = user.mapToUser(),
        recommendCards = recommendCards.map(CardItem::mapToCard)
    )
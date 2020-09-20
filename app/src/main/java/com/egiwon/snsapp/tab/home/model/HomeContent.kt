package com.egiwon.snsapp.tab.home.model

import com.egiwon.snsapp.tab.imagefeed.model.Card

data class HomeContent(
    val popularCards: List<Card> = emptyList(),
    val popularUsers: List<User> = emptyList()
)
package com.egiwon.snsapp.tab.home.model

import com.egiwon.snsapp.data.entity.UserItem

data class User(
    val nickname: String = "",
    val introduction: String = "",
    val id: Int = -1
)

fun UserItem.mapToUser(): User = User(
    id = id,
    nickname = nickname,
    introduction = introduction
)

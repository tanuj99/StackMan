package com.tanuj.stackman.datasource.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("items")
    val items: Items
)

@Serializable
data class Items(
    @SerialName("score")
    val score: Long,
    @SerialName("question_id")
    val question_id: Long,
    @SerialName("title")
    val title: String
)
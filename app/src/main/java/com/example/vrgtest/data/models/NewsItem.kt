package com.example.vrgtest.data.models

import kotlinx.datetime.LocalDateTime

data class NewsSource(
    val id: String,
    val name: String
)

data class NewsItem(
    val source: NewsSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: LocalDateTime,
    val content: String
)
package com.flexath.newsify.data.remote.dto

import com.flexath.newsify.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
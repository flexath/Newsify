package com.flexath.newsify.presentation.bookmark

import com.flexath.newsify.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)

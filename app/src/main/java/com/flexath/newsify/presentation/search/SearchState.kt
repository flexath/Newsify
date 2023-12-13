package com.flexath.newsify.presentation.search

import androidx.paging.PagingData
import com.flexath.newsify.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
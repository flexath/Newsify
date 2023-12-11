package com.flexath.newsify.domain.repository

import androidx.paging.PagingData
import com.flexath.newsify.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>) : Flow<PagingData<Article>>
}
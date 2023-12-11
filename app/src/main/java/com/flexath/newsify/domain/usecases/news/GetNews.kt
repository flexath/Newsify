package com.flexath.newsify.domain.usecases.news

import androidx.paging.PagingData
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val repository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return repository.getNews(sources)
    }
}
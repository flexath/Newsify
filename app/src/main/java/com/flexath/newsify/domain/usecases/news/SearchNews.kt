package com.flexath.newsify.domain.usecases.news

import androidx.paging.PagingData
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return repository.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}
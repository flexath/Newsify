package com.flexath.newsify.domain.usecases.news

import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> = repository.getArticles()
}
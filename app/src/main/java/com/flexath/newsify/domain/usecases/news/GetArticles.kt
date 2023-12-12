package com.flexath.newsify.domain.usecases.news

import com.flexath.newsify.data.local.NewsDao
import com.flexath.newsify.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val dao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> = dao.getArticles()
}
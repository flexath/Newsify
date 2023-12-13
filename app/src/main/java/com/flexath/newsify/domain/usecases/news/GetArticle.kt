package com.flexath.newsify.domain.usecases.news

import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.repository.NewsRepository

class GetArticle(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(url:String): Article? = repository.getArticle(url)
}
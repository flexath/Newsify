package com.flexath.newsify.domain.usecases.news

import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.repository.NewsRepository

class DeleteArticle(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.deleteArticle(article = article)
    }
}
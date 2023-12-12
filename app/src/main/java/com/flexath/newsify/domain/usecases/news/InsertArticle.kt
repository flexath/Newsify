package com.flexath.newsify.domain.usecases.news

import com.flexath.newsify.data.local.NewsDao
import com.flexath.newsify.domain.model.Article

class InsertArticle(
    private val dao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        dao.insertArticle(article)
    }
}
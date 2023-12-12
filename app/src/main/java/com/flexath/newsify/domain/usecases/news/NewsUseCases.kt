package com.flexath.newsify.domain.usecases.news

data class NewsUseCases(
    val getNewsUseCases: GetNews,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles
)
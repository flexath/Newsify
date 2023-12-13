package com.flexath.newsify.domain.usecases.news

data class NewsUseCases(
    val getNewsUseCases: GetNews,
    val searchNews: SearchNews,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)
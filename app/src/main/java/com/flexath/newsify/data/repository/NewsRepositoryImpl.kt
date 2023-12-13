package com.flexath.newsify.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.flexath.newsify.data.local.NewsDao
import com.flexath.newsify.data.remote.api.NewsApi
import com.flexath.newsify.data.remote.NewsPagingSource
import com.flexath.newsify.data.remote.SearchNewsPagingSource
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    val newsApi: NewsApi,
    val dao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun insertArticle(article: Article) {
        dao.insertArticle(article = article)
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article = article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return dao.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return dao.getArticle(url = url)
    }
}
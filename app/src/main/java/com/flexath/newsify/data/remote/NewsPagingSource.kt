package com.flexath.newsify.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flexath.newsify.domain.model.Article

class NewsPagingSource(
    val newsApi: NewsApi,
    val sources: String
) : PagingSource<Int, Article>() {

    private var totalPageCount  = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newResponse = newsApi.getNews(
                page = page,
                sources = sources
            )
            totalPageCount += newResponse.articles.size
            val articles = newResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else -1,
                nextKey = if(totalPageCount == newResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}
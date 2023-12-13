package com.flexath.newsify.data.remote.api

import com.flexath.newsify.data.remote.dto.NewsResponse
import com.flexath.newsify.util.Constants.API_KEY
import com.flexath.newsify.util.Constants.PARAM_API_KEY
import com.flexath.newsify.util.Constants.PARAM_PAGE
import com.flexath.newsify.util.Constants.PARAM_SOURCES
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query(PARAM_PAGE) page: Int,
        @Query(PARAM_SOURCES) sources: String,
        @Query(PARAM_API_KEY) apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
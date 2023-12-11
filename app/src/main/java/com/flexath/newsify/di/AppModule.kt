package com.flexath.newsify.di

import android.content.Context
import com.flexath.newsify.data.manager.LocalUserManagerImpl
import com.flexath.newsify.data.remote.NewsApi
import com.flexath.newsify.data.repository.NewsRepositoryImpl
import com.flexath.newsify.domain.manager.LocalUserManager
import com.flexath.newsify.domain.repository.NewsRepository
import com.flexath.newsify.domain.usecases.app_entry.AppEntryUseCases
import com.flexath.newsify.domain.usecases.app_entry.ReadAppEntry
import com.flexath.newsify.domain.usecases.app_entry.SaveAppEntry
import com.flexath.newsify.domain.usecases.news.GetNews
import com.flexath.newsify.domain.usecases.news.GetNewsUseCases
import com.flexath.newsify.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(@ApplicationContext context: Context): LocalUserManager =
        LocalUserManagerImpl(context)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository =
        NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideGetNewUseCases(repository: NewsRepository): GetNewsUseCases = GetNewsUseCases(
        getNewsUseCases = GetNews(repository)
    )

}
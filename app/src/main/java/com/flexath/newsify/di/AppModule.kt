package com.flexath.newsify.di

import android.content.Context
import androidx.room.Room
import com.flexath.newsify.data.local.NewsDao
import com.flexath.newsify.data.local.NewsDatabase
import com.flexath.newsify.data.local.NewsTypeConverter
import com.flexath.newsify.data.manager.LocalUserManagerImpl
import com.flexath.newsify.data.remote.api.NewsApi
import com.flexath.newsify.data.repository.NewsRepositoryImpl
import com.flexath.newsify.domain.manager.LocalUserManager
import com.flexath.newsify.domain.repository.NewsRepository
import com.flexath.newsify.domain.usecases.app_entry.AppEntryUseCases
import com.flexath.newsify.domain.usecases.app_entry.ReadAppEntry
import com.flexath.newsify.domain.usecases.app_entry.SaveAppEntry
import com.flexath.newsify.domain.usecases.news.DeleteArticle
import com.flexath.newsify.domain.usecases.news.GetArticle
import com.flexath.newsify.domain.usecases.news.GetNews
import com.flexath.newsify.domain.usecases.news.InsertArticle
import com.flexath.newsify.domain.usecases.news.NewsUseCases
import com.flexath.newsify.domain.usecases.news.GetArticles
import com.flexath.newsify.domain.usecases.news.SearchNews
import com.flexath.newsify.util.Constants
import com.flexath.newsify.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideNewsRepository(newsApi: NewsApi, dao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi = newsApi,dao)

    @Provides
    @Singleton
    fun provideNewUseCases(
        repository: NewsRepository,
        dao: NewsDao
    ): NewsUseCases = NewsUseCases(
        getNewsUseCases = GetNews(repository),
        searchNews = SearchNews(repository),
        insertArticle = InsertArticle(repository),
        deleteArticle = DeleteArticle(repository),
        getArticles = GetArticles(repository),
        getArticle = GetArticle(repository)
    )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = NewsDatabase::class.java,
            name = DB_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(database: NewsDatabase): NewsDao = database.newsDao
}
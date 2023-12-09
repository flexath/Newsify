package com.flexath.newsify.di

import android.content.Context
import com.flexath.newsify.data.manager.LocalUserManagerImpl
import com.flexath.newsify.domain.manager.LocalUserManager
import com.flexath.newsify.domain.usecases.AppEntryUseCases
import com.flexath.newsify.domain.usecases.ReadAppEntry
import com.flexath.newsify.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
}
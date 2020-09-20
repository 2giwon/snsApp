package com.egiwon.snsapp.di

import com.egiwon.snsapp.data.AuthRepository
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.data.auth.AuthDataSource
import com.egiwon.snsapp.data.auth.AuthRepositoryImpl
import com.egiwon.snsapp.data.content.ContentDataSource
import com.egiwon.snsapp.data.content.ContentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesContentRepository(contentDataSource: ContentDataSource): ContentRepository =
        ContentRepositoryImpl(contentDataSource)

    @Provides
    @Singleton
    fun providesAuthRepository(authDataSource: AuthDataSource): AuthRepository =
        AuthRepositoryImpl(authDataSource)
}
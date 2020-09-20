package com.egiwon.snsapp.di

import com.egiwon.snsapp.data.auth.AuthDataSource
import com.egiwon.snsapp.data.auth.AuthService
import com.egiwon.snsapp.data.content.ContentDataSource
import com.egiwon.snsapp.data.content.ContentService
import com.egiwon.snsapp.data.source.AuthRemoteDataSource
import com.egiwon.snsapp.data.source.ContentRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideContentRemoteDataSource(contentService: ContentService): ContentDataSource =
        ContentRemoteDataSource(contentService)

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(authService: AuthService): AuthDataSource =
        AuthRemoteDataSource(authService)
}
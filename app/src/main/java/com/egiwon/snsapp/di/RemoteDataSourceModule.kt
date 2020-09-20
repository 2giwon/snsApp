package com.egiwon.snsapp.di

import com.egiwon.snsapp.data.ContentDataSource
import com.egiwon.snsapp.data.ContentService
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
    fun provideRemoteDataSource(contentService: ContentService): ContentDataSource =
        ContentRemoteDataSource(contentService)
}
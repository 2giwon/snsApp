package com.egiwon.snsapp.di

import com.egiwon.snsapp.data.ContentDataSource
import com.egiwon.snsapp.data.ContentRepository
import com.egiwon.snsapp.data.ContentRepositoryImpl
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

}
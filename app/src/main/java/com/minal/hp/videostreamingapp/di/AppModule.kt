package com.minal.hp.videostreamingapp.di

import android.app.Application
import android.content.Context
import com.minal.hp.videostreamingapp.model.repository.IVideosRepository
import com.minal.hp.videostreamingapp.model.repository.VideoRepository
import com.minal.hp.videostreamingapp.model.source.local.IVideosPersistenceSource
import com.minal.hp.videostreamingapp.model.source.local.VideoPersistenceSource
import com.minal.hp.videostreamingapp.model.source.remote.IVideosNetworkSource
import com.minal.hp.videostreamingapp.model.source.remote.VideoNetworkSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun getVideosRepository(source: VideoRepository): IVideosRepository

    @Binds
    @Singleton
    abstract fun getVideosNetworkSource(source: VideoNetworkSource): IVideosNetworkSource

    @Binds
    @Singleton
    abstract fun getVideosPersistenceSource(source: VideoPersistenceSource): IVideosPersistenceSource


    companion object {
        @Singleton
        @Provides
        fun provideContext(application: Application): Context
                = application.applicationContext

    }
}
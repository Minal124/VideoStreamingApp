package com.minal.hp.videostreamingapp.di

import android.content.Context
import androidx.room.Room
import com.minal.hp.videostreamingapp.model.source.local.dao.IVideoDao
import com.minal.hp.videostreamingapp.model.source.local.database.VideosRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideVideoDao(videosRoomDatabase: VideosRoomDatabase): IVideoDao {
        return videosRoomDatabase.videosDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): VideosRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            VideosRoomDatabase::class.java,
            "video_db"
        ).build()
    }
}
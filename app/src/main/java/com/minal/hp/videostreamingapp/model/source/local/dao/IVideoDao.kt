package com.minal.hp.videostreamingapp.model.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface IVideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideosInfo>)

    @Query("SELECT * FROM Videos")
    fun getAllVideos(): Flow<List<VideosInfo>>
}
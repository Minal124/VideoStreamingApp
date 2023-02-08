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

    @Query("UPDATE Videos SET likes = :likes, isLiked = :isLiked WHERE id =:id")
    suspend fun updateLikesData(likes: Int, isLiked: Boolean, id: Int)
}
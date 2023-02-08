package com.minal.hp.videostreamingapp.model.source.local

import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import kotlinx.coroutines.flow.Flow

interface IVideosPersistenceSource {
    suspend fun saveVideos(videoList: List<VideosInfo>)
    fun getVideos(): Flow<List<VideosInfo>>
    suspend fun updateLikesData(likes: Int, isLiked: Boolean, id: Int)
}

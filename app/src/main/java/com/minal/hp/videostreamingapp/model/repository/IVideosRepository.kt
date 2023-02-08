package com.minal.hp.videostreamingapp.model.repository

import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import kotlinx.coroutines.flow.Flow

interface IVideosRepository {
    suspend fun  fetchAndUpdateVideos()
    fun getVideos(): Flow<List<VideosInfo>>
    suspend fun updateLikesData(likes: Int, isLiked: Boolean, id: Int)
}
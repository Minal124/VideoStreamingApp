package com.minal.hp.videostreamingapp.model.source.remote

import com.minal.hp.videostreamingapp.model.dto.VideoDetailsDTO
import com.minal.hp.videostreamingapp.model.repository.BaseRepository
import com.minal.hp.videostreamingapp.util.API_KEY
import javax.inject.Inject

class VideoNetworkSource @Inject constructor(private val api: Api) : IVideosNetworkSource,
    BaseRepository() {
    override suspend fun getVideos(): List<VideoDetailsDTO>{
        return safeApiCall(
            call = { api.getLatestVideos(API_KEY) },
            error = "Error fetching news"
        )?.hits ?: emptyList()
    }
}

sealed class Output<out T : Any> {
    data class Success<out T : Any>(val output: T) : Output<T>()
    data class Error(val exception: Exception) : Output<Nothing>()
}
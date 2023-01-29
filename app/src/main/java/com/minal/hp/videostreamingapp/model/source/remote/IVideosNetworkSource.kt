package com.minal.hp.videostreamingapp.model.source.remote

import com.minal.hp.videostreamingapp.model.dto.VideoDetailsDTO

interface IVideosNetworkSource {
    suspend fun  getVideos(): List<VideoDetailsDTO>
}

package com.minal.hp.videostreamingapp.model.source.remote

import com.minal.hp.videostreamingapp.model.dto.VideoDataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("videos/")
    suspend fun getLatestVideos(
        @Query("key") key: String
    ): Response<VideoDataDTO>
}
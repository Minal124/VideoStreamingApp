package com.minal.hp.videostreamingapp.model.repository

import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.model.datamodel.toVideosData
import com.minal.hp.videostreamingapp.model.source.local.IVideosPersistenceSource
import com.minal.hp.videostreamingapp.model.source.remote.IVideosNetworkSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val localSource: IVideosPersistenceSource,
    private val remoteSource: IVideosNetworkSource,
): IVideosRepository {
    override suspend fun fetchAndUpdateVideos() {
        val remoteData = remoteSource.getVideos().map{
            it.toVideosData()
        }
        localSource.saveVideos(remoteData)
    }

    override fun getVideos(): Flow<List<VideosInfo>> =
        localSource.getVideos()

}
package com.minal.hp.videostreamingapp.model.source.local

import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.model.source.local.dao.IVideoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoPersistenceSource @Inject constructor(
    private val videosDao: IVideoDao
): IVideosPersistenceSource {

    override suspend fun saveVideos(videoList: List<VideosInfo>) {
        videosDao.insertVideos(videoList)
    }

    override fun getVideos(): Flow<List<VideosInfo>> =
        videosDao.getAllVideos()

}
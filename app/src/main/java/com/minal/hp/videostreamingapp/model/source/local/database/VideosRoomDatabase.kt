package com.minal.hp.videostreamingapp.model.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.model.source.local.dao.IVideoDao


@Database(
    entities = [VideosInfo::class],
    version = 1,
    exportSchema = false
)
abstract class VideosRoomDatabase : RoomDatabase() {
    abstract fun videosDao(): IVideoDao
}

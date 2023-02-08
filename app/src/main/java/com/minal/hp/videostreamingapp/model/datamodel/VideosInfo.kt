package com.minal.hp.videostreamingapp.model.datamodel

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.minal.hp.videostreamingapp.model.dto.VideoDetailsDTO

@Entity(tableName = "Videos")
data class VideosInfo(
    @PrimaryKey val id: Int,
    @ColumnInfo val pageURL: String,
    @ColumnInfo val type: String,
    @ColumnInfo val tags: String?,
    @ColumnInfo val duration: Int,
    @ColumnInfo val pictureId: String,
    @Embedded val videos: VideosDetailInfo,
    @ColumnInfo val views: Int,
    @ColumnInfo val downloads: Int,
    @ColumnInfo val likes: Int,
    @ColumnInfo val comments: Int,
    @ColumnInfo val userId: Int,
    @ColumnInfo val user: String,
    @ColumnInfo val userImageURL: String,
    @ColumnInfo val isLiked: Boolean,
)

data class VideosDetailInfo(
    val url: String,
    val width: Int,
    val height: Int,
    val size: Int,
)

fun VideoDetailsDTO.toVideosData() : VideosInfo {

    val videoDataInfo =
        VideosDetailInfo(
            videos.small.url,
            videos.small.width,
            videos.small.height,
            videos.small.size
        )

    return VideosInfo(
        id = this.id,
        pageURL = this.pageURL,
        type = this.type,
        tags = this.tags,
        duration = this.duration,
        pictureId = this.pictureId,
        videos = videoDataInfo,
        views = this.views,
        downloads = this.downloads,
        likes = this.likes,
        comments = this.comments,
        userId = this.userId,
        user = this.user,
        userImageURL = this.userImageURL,
        isLiked = false
    )
}

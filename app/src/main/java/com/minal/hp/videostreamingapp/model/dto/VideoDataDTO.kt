package com.minal.hp.videostreamingapp.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class VideoDataDTO(
    @Json(name = "total") val total: Int,
    @Json(name = "totalHits") val totalHits: Int,
    @Json(name = "hits") val hits: List<VideoDetailsDTO>,
)

@JsonClass(generateAdapter = true)
data class VideoDetailsDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "pageURL") val pageURL: String,
    @Json(name = "type") val type: String,
    @Json(name = "tags") val tags: String,
    @Json(name = "duration") val duration: Int,
    @Json(name = "picture_id") val pictureId: String,
    @Json(name = "videos") val videos: VideoUrlDTO,
    @Json(name = "views") val views: Int,
    @Json(name = "downloads") val downloads: Int,
    @Json(name = "likes") val likes: Int,
    @Json(name = "comments") val comments: Int,
    @Json(name = "user_id") val userId: Int,
    @Json(name = "user") val user: String,
    @Json(name = "userImageURL") val userImageURL: String
)

@JsonClass(generateAdapter = true)
data class VideoUrlDTO(
    @Json(name = "large") val large: VideoUrlDataDTO,
    @Json(name = "medium") val medium: VideoUrlDataDTO,
    @Json(name = "small") val small: VideoUrlDataDTO,
)

@JsonClass(generateAdapter = true)
data class VideoUrlDataDTO(
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "size") val size: Int
)
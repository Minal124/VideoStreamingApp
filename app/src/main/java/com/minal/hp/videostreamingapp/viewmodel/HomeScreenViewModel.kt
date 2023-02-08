package com.minal.hp.videostreamingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.model.repository.IVideosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private val logger get() = Timber.tag("HomeScreenViewModel")

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val videosRepository : IVideosRepository
): ViewModel(){

    private val _videosState: MutableStateFlow<VideoViewState> = MutableStateFlow(VideoViewState())
    val videosState = _videosState.asStateFlow()

    init {

        _videosState.value = VideoViewState(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                videosRepository.getVideos()
                    .flowOn(Dispatchers.IO)
                    .collect{
                        _videosState.value = VideoViewState(
                            isLoading = false,
                            videosList = it
                        )
                    }
                logger.d("fetch Videos Data")
            } catch (e: Exception) {
                _videosState.value = VideoViewState(
                    isLoading = false,
                    errorString = "Failure in fetching Schema"
                )
                logger.e(e, "Failure in fetching Schema")
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                videosRepository.fetchAndUpdateVideos()
            } catch (e: Exception) {
                logger.e(e, "Failure in updating schema")
            }
        }
    }

    fun handleLikeEvent(id: Int) {
        viewModelScope.launch {
            val isLiked = !_videosState.value.videosList[id].isLiked
            val likes =  _videosState.value.videosList[id].likes
            val likeCount = if (isLiked) likes + 1 else likes - 1
            videosRepository.updateLikesData(likeCount, isLiked, id)
        }
    }

    fun handleDoubleTapLikeEvent(id: Int) {
        viewModelScope.launch {
            val likeCount = _videosState.value.videosList[id].likes+1
            videosRepository.updateLikesData(likeCount, true, id)
        }
    }
}

data class VideoViewState (
    val isLoading:Boolean = true,
    val videosList: List<VideosInfo> = emptyList(),
    val errorString: String? = null
)
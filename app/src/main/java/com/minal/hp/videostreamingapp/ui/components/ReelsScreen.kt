package com.minal.hp.videostreamingapp.ui.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_ALWAYS
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.util.ReelIcon
import com.minal.hp.videostreamingapp.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import com.minal.hp.videostreamingapp.R


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReelsScreen() {

    val state = rememberPagerState()
    var isMuted by remember {
        mutableStateOf(false)
    }

    val isFirstItem by remember(state) {
        derivedStateOf {
            state.currentPage == 0
        }
    }

    val viewModel = viewModel<HomeScreenViewModel>()
    val videoState = viewModel.videosState.collectAsState()
    if (videoState.value.isLoading) {
        //show Loader
    }
    Box {
        VerticalPager(
            count = videoState.value.videosList.size,
            state = state,
            horizontalAlignment = Alignment.CenterHorizontally,
            itemSpacing = 10.dp
        ) { page ->

            val shouldPlay by remember(state) {
                derivedStateOf {
                    (abs(currentPageOffset) < .5 && currentPage == page) ||
                            (abs(currentPageOffset) > .5 && state.targetPage == page)
                }
            }

            ReelPlayer(
                reel = videoState.value.videosList[page],
                shouldPlay = shouldPlay,
                isMuted = isMuted,
                isScrolling = state.isScrollInProgress,
                onMuted = {
                    isMuted = it
                },
                onDoubleTap = {
                }
            )

            ReelItem(
                reel = videoState.value.videosList[page],
                onIconClicked = { icon ->
                    when (icon) {
                        ReelIcon.CAMERA -> {
                            //:TODO
                        }
                        ReelIcon.SHARE -> {
                            //:TODO
                        }
                        ReelIcon.MORE_OPTIONS -> {
                            //:TODO
                        }
                        ReelIcon.AUDIO -> {
                            //:TODO
                        }
                        ReelIcon.COMMENT -> {
                            //:TODO
                        }
                        ReelIcon.LIKE -> {
                            //:TODO
                        }
                    }
                }
            )

            ReelHeader(
                isFirstItem = isFirstItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReelPlayer(
    reel: VideosInfo,
    shouldPlay: Boolean,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
    onDoubleTap: (Boolean) -> Unit,
    isScrolling: Boolean
) {
    val exoPlayer = rememberExoPlayerWithLifecycle(reel.videos.url)
    val playerView = rememberPlayerView(exoPlayer)
    var volumeIconVisibility by remember { mutableStateOf(false) }
    var likeIconVisibility by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box {
        AndroidView(
            factory = { playerView },
            modifier = Modifier
                .pointerInput(reel.isLiked, isMuted) {
                    detectTapGestures(
                        onDoubleTap = {
                            onDoubleTap(true)
                            coroutineScope.launch {
                                likeIconVisibility = true
                                delay(800)
                                likeIconVisibility = false
                            }
                        },
                        onTap = {
                            if (exoPlayer.playWhenReady) {
                                if (isMuted.not()) {
                                    exoPlayer.volume = 0f
                                    onMuted(true)
                                } else {
                                    exoPlayer.volume = 1f
                                    onMuted(false)
                                }
                                coroutineScope.launch {
                                    volumeIconVisibility = true
                                    delay(800)
                                    volumeIconVisibility = false
                                }
                            }
                        },
                        onPress = {
                            if (!isScrolling) {
                                exoPlayer.playWhenReady = false
                                awaitRelease()
                                exoPlayer.playWhenReady = true
                            }
                        },
                        onLongPress = {}
                    )
                },
            update = {
                exoPlayer.volume = if (isMuted) 0f else 1f
                exoPlayer.playWhenReady = shouldPlay
            }
        )

        AnimatedVisibility(
            visible = likeIconVisibility,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.White.copy(0.90f),
                modifier = Modifier
                    .size(100.dp)
            )
        }

        if (volumeIconVisibility) {
            Icon(
                painter = painterResource(id = if (isMuted) R.drawable.ic_volume_off  else R.drawable.ic_volume_on),
                contentDescription = null,
                tint = Color.White.copy(0.75f),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp)
            )
        }

    }

    DisposableEffect(key1 = true) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun rememberPlayerView(
    exoPlayer: ExoPlayer
): PlayerView {

    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            player = exoPlayer
            setShowBuffering(SHOW_BUFFERING_ALWAYS)
        }
    }
    DisposableEffect(key1 = true) {
        onDispose {
            playerView.player = null
        }
    }
    return playerView
}


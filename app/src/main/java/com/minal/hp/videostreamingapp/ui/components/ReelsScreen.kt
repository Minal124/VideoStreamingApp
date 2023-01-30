package com.minal.hp.videostreamingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.minal.hp.videostreamingapp.util.ReelIcon
import com.minal.hp.videostreamingapp.viewmodel.HomeScreenViewModel
import kotlin.math.abs


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReelsScreen() {

    val viewModel = viewModel<HomeScreenViewModel>()
    val videoState = viewModel.videosState.collectAsState()

    val state = rememberPagerState()
    var isMuted by remember {
        mutableStateOf(false)
    }
    val onLiked = remember {
        { index: Int, liked: Boolean ->
            videoState.value.videosList[index].isLiked = liked
        }
    }

    val isFirstItem by remember(state) {
        derivedStateOf {
            state.currentPage == 0
        }
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
                    onLiked(page, it)
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
                            onLiked(page, !videoState.value.videosList[page].isLiked)
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
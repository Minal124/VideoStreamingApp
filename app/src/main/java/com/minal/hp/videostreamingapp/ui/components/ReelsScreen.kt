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
    val videoState by viewModel.videosState.collectAsState()

    val state = rememberPagerState()
    var isMuted by remember {
        mutableStateOf(false)
    }

    val isFirstItem by remember(state) {
        derivedStateOf {
            state.currentPage == 0
        }
    }

    Box {
        VerticalPager(
            count = videoState.videosList.size,
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
                reel = videoState.videosList[page],
                shouldPlay = shouldPlay,
                isMuted = isMuted,
                isScrolling = state.isScrollInProgress,
                onMuted = {
                    isMuted = it
                },
                onDoubleTap = {
                    viewModel.handleDoubleTapLikeEvent(page)
                }
            )

            ReelItem(
                reel = videoState.videosList[page],
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
                            viewModel.handleLikeEvent(page)
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
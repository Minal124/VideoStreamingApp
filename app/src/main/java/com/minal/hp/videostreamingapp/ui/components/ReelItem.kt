package com.minal.hp.videostreamingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.util.ReelIcon

@Composable
fun ReelItem(
    reel: VideosInfo,
    onIconClicked: (ReelIcon) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(0.5f)
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
        ) {

            ReelsInfoItems(
                reel
            ) {
                onIconClicked(it)
            }

        }
    }
}

@Composable
fun ReelsInfoItems(
    reelInfo: VideosInfo,
    onIconClicked: (ReelIcon) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            ReelsBottomItems(
                modifier = Modifier.fillMaxWidth(.8f),
                reelInfo = reelInfo
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReelsColumnIcons(
                reelInfo = reelInfo,
                onIconClicked = onIconClicked
            )
        }
    }
}

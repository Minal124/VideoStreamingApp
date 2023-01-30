package com.minal.hp.videostreamingapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minal.hp.videostreamingapp.R
import com.minal.hp.videostreamingapp.util.ReelIcon

@Composable
fun ReelHeader(
    modifier: Modifier = Modifier,
    isFirstItem: Boolean,
    onCameraIconClicked: (ReelIcon) -> Unit
) {
    Box(
        modifier = modifier
            .padding(PaddingValues(16.dp, 12.dp)),
    ) {
        AnimatedVisibility(
            visible = isFirstItem,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = "Reels",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
            )
        }

        IconButton(
            onClick = { onCameraIconClicked(ReelIcon.CAMERA) },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}
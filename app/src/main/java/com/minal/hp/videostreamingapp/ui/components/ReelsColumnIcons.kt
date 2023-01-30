package com.minal.hp.videostreamingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.minal.hp.videostreamingapp.model.datamodel.VideosInfo
import com.minal.hp.videostreamingapp.util.ReelIcon
import com.minal.hp.videostreamingapp.R


@Composable
fun ReelsColumnIcons(
    reelInfo: VideosInfo,
    onIconClicked: (ReelIcon) -> Unit
) {

    TextedIcon(
        iconVector = if (!reelInfo.isLiked) Icons.Outlined.FavoriteBorder else Icons.Filled.Favorite,
        text = reelInfo.likes.toString(),
        modifier = Modifier.size(30.dp),
        tint = if (reelInfo.isLiked) Color.Red else Color.White,
        onIconClicked = {
            onIconClicked(ReelIcon.LIKE)
        }
    )
    TextedIcon(
        iconRes = R.drawable.ic_chat_bubble,
        text = reelInfo.comments.toString(),
        modifier = Modifier.size(30.dp),
        onIconClicked = {
            onIconClicked(ReelIcon.COMMENT)
        }
    )

    IconButton(onClick = { onIconClicked(ReelIcon.SHARE) }) {
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
    IconButton(onClick = { onIconClicked(ReelIcon.MORE_OPTIONS) }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(30.dp)
                .rotate(90f),
        )
    }
    IconButton(onClick = { onIconClicked(ReelIcon.AUDIO) }) {
        Image(
            painter = rememberAsyncImagePainter(reelInfo.userImageURL),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

package com.minal.hp.videostreamingapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun TextedIcon(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    text: String,
    tint: Color = Color.White,
    contentDescription: String? = null,
    onIconClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {
            onIconClicked()
        }) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier
            )
        }
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun TextedIcon(
    modifier: Modifier = Modifier,
    iconVector: ImageVector,
    text: String,
    tint: Color = Color.White,
    contentDescription: String? = null,
    onIconClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {
            onIconClicked()
        }) {
            Icon(
                imageVector = iconVector,
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier
            )
        }
        Text(
            text = text,
            color = Color.White
        )
    }
}
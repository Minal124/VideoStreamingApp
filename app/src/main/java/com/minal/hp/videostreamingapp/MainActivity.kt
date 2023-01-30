package com.minal.hp.videostreamingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import com.minal.hp.videostreamingapp.ui.components.ReelsScreen
import com.minal.hp.videostreamingapp.ui.theme.VideoStreamingAppTheme
import com.minal.hp.videostreamingapp.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeScreenViewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeScreenViewModel.refresh()
        setContent {
            VideoStreamingAppTheme {
                Surface {
                   ReelsScreen()
                }
            }
        }
    }
}

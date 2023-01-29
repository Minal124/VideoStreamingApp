package com.minal.hp.videostreamingapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VideoStreamingApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
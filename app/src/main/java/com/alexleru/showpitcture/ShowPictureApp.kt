package com.alexleru.showpitcture

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShowPictureApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ShowPictureApp)

        }
    }
}
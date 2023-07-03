package com.alexleru.showpitcture

import android.app.Application
import com.alexleru.showpictrure.presentation.view.di.catsPresentationModule
import com.alexleru.showpicture.data.di.catsDataModule
import com.alexleru.showpicture.domain.di.catDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShowPictureApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ShowPictureApp)
            modules(
                catDomainModule,
                catsDataModule,
                catsPresentationModule
            )
        }
    }
}
package io.space.application

import android.app.Application
import io.space.data.di.injectSpaceDataKoinModule
import io.space.presentation.di.injectSpacePresentationKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SpaceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@SpaceApplication) }

        injectSpaceDataKoinModule()
        injectSpacePresentationKoinModule()
    }
}

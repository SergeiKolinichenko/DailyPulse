package my.mvi.dailypulse.android

import android.app.Application
import my.mvi.dailypulse.android.di.databaseModule
import my.mvi.dailypulse.android.di.viewModelModule
import my.mvi.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}
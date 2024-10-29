package my.mvi.dailypulse.android.di

import my.mvi.dailypulse.database.DailyPulseDatabase
import my.mvi.dailypulse.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { DatabaseDriverFactory(androidContext()).createDriver() }

    single { DailyPulseDatabase(get()) }

}
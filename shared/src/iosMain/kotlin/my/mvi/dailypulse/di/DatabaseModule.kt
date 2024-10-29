package my.mvi.dailypulse.di

import my.mvi.dailypulse.database.DailyPulseDatabase
import my.mvi.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseDriverFactory().createDriver() }
    single { DailyPulseDatabase(get()) }
}
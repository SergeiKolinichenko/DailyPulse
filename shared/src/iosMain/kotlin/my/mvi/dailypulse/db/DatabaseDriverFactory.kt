package my.mvi.dailypulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import my.mvi.dailypulse.database.DailyPulseDatabase

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema =  DailyPulseDatabase.Schema,
            name = "DailyPulse.Database.db"
        )
}
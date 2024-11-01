package my.mvi.dailypulse.sources.data

import my.mvi.dailypulse.database.DailyPulseDatabase

class SourcesDataSource(
    private val database: DailyPulseDatabase
) {

    fun getAllSources(): List<SourceRaw> =
        database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourceRaw>) = database.dailyPulseDatabaseQueries.transaction {
        sources.forEach { sourceRaw -> insertSource(sourceRaw) }
    }

    fun cleanSources() = database.dailyPulseDatabaseQueries.removeAllSource()

    private fun insertSource(source: SourceRaw) =
        database.dailyPulseDatabaseQueries.insertSource(
            name = source.name,
            desc = source.desc,
            origin = source.country + "-" + source.language
        )

    private fun mapToSourceRaw(
        name: String,
        desc: String,
        origin: String
    ) = SourceRaw(
        name = name,
        desc = desc,
        language = origin.substringAfter("-"),
        country = origin.substringBefore("-")
    )
}
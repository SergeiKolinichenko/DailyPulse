package my.mvi.dailypulse.sources.data

import my.mvi.dailypulse.sources.domain.Source
import my.mvi.dailypulse.sources.domain.SourcesRepository

class SourcesRepositoryImpl(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService,
): SourcesRepository {

    override suspend fun getSources(): List<Source> {
        val sourcesDb = dataSource.getAllSources()
        if (sourcesDb.isEmpty()) return fetchSources()
        return sourcesDb.mapSources()
    }

    private suspend fun fetchSources(): List<Source> {
        val fetchedSources = service.fetchSources()
        dataSource.insertSources(fetchedSources)
        return fetchedSources.mapSources()
    }

    private fun List<SourceRaw>.mapSources(): List<Source> = this.map { raw ->
        Source(
            name = raw.name,
            desc = raw.desc,
            origin = raw.country + "-" + raw.language
        )
    }
}
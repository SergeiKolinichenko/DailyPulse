package my.mvi.dailypulse.sources.domain

interface SourcesRepository {
    suspend fun getSources(): List<Source>
}
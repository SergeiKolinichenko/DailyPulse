package my.mvi.dailypulse.sources.domain

class SourcesUseCase(private val repo: SourcesRepository) {

    suspend fun getSources(): List<Source> = repo.getSources()

}
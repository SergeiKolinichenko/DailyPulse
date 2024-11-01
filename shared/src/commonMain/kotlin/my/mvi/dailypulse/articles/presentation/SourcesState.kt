package my.mvi.dailypulse.articles.presentation

import my.mvi.dailypulse.sources.domain.Source

data class SourcesState(
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)

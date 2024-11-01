package my.mvi.dailypulse.sources.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.mvi.dailypulse.BaseViewModel
import my.mvi.dailypulse.articles.presentation.SourcesState
import my.mvi.dailypulse.sources.domain.SourcesUseCase

class SourcesViewModel(
    private var sourcesUseCase: SourcesUseCase
): BaseViewModel() {

    private val _sourcesState: MutableStateFlow<SourcesState> = MutableStateFlow(SourcesState(loading = true))
    val sourcesState: StateFlow<SourcesState> = _sourcesState

    init {
        getSources()
    }

    private fun getSources() {
        scope.launch {
            _sourcesState.emit(SourcesState(loading = true))
            val fetchedSources = sourcesUseCase.getSources()
            _sourcesState.emit(SourcesState(sources = fetchedSources))
        }
    }

}
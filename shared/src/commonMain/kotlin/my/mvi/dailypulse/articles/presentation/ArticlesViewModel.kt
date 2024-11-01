package my.mvi.dailypulse.articles.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.mvi.dailypulse.BaseViewModel
import my.mvi.dailypulse.articles.domain.ArticlesUseCase

class ArticlesViewModel(
    private var articlesUseCase: ArticlesUseCase
): BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            _articlesState.emit(ArticlesState(loading = true))
            val fetchedArticles = articlesUseCase.getArticles(forceFetch)
            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

}
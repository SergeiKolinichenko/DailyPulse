package my.mvi.dailypulse.articles.presentation

import my.mvi.dailypulse.articles.domain.Article

data class ArticlesState (
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)

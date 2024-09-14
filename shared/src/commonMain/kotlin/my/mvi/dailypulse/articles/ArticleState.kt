package my.mvi.dailypulse.articles

data class ArticleState(
  val loading: Boolean = false,
  val articles: List<Article> = emptyList(),
  val error: String? = null
)

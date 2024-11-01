package my.mvi.dailypulse.articles.domain

class ArticlesUseCase(private val repo: ArticlesRepository) {

    suspend fun getArticles(forceFetch: Boolean): List<Article> = repo.getArticles(forceFetch)

}
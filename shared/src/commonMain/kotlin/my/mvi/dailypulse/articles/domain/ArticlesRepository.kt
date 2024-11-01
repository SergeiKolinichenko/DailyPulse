package my.mvi.dailypulse.articles.domain

interface ArticlesRepository {
    suspend fun getArticles(forceFetch: Boolean): List<Article>
}
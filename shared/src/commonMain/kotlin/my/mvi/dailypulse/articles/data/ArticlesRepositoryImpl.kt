package my.mvi.dailypulse.articles.data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import my.mvi.dailypulse.articles.domain.Article
import my.mvi.dailypulse.articles.domain.ArticlesRepository
import kotlin.math.abs

class ArticlesRepositoryImpl(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService,
): ArticlesRepository {

    override suspend fun getArticles(forceFetch: Boolean): List<Article> {
        if (forceFetch) {
            dataSource.cleanArticles()
            return fetchArticles()
        }
        val articlesDb = dataSource.getAllArticles()
        if (articlesDb.isEmpty()) return fetchArticles()
        return articlesDb.mapArticles()
    }

    private suspend fun fetchArticles(): List<Article> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles.mapArticles()
    }

    private fun List<ArticleRaw>.mapArticles(): List<Article> = this.map { raw ->
        Article(
            raw.title,
            raw.desc ?: "Click to find out more",
            getDaysAgoString(raw.date),
            raw.imageUrl
                ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        )
    }

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }

}
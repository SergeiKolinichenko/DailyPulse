package my.mvi.dailypulse.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

/** Created by Sergei Kolinichenko on 18.09.2024 at 21:08 (GMT+3) **/

class ArticlesUseCase(
  private val service: ArticlesService
) {

  suspend fun getArticles() =
    service.getArticles()
      .mapArticles()

  private fun List<ArticleRow>.mapArticles() =
    map{ article ->
      Article(
        title = article.title,
        desc = article.desc ?: "Click to find out more...",
        imageUrl = article.imageUrl ?: DEFAULT_IMG_URL,
        date = article.date,
      )
    }

  private fun String.getDaysAgoString(): String {
    val tz = TimeZone.currentSystemDefault()
    val today = Clock.System.todayIn(tz)
    val days = today.daysUntil(Instant.parse(this).toLocalDateTime(tz).date)

    return when {
      abs(days) > 1 -> "$days days ago"
      abs(days) == 1 -> "Yesterday"
      else -> "Today"
    }
  }

  companion object {
    private const val DEFAULT_IMG_URL = "\"https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080\""
  }
}
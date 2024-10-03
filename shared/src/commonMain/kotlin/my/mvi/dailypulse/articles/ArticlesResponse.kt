package my.mvi.dailypulse.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Created by Sergei Kolinichenko on 16.09.2024 at 20:53 (GMT+3) **/

@Serializable
data class ArticlesResponse(
  @SerialName("status")
  val status: String,

  @SerialName("totalResults")
  val results: Int,

  @SerialName("articles")
  val articles: List<ArticleRow>
)
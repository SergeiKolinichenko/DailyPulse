package my.mvi.dailypulse.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Created by Sergei Kolinichenko on 17.09.2024 at 13:39 (GMT+3) **/

@Serializable
data class ArticleRow(
  @SerialName("author")
  val author: String?,

  @SerialName("title")
  val title: String,

  @SerialName("description")
  val desc: String?,

  @SerialName("urlToImage")
  val imageUrl: String?,

  @SerialName("publishedAt")
  val date: String,

  @SerialName("content")
  val content: String?
)

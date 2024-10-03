package my.mvi.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/** Created by Sergei Kolinichenko on 17.09.2024 at 19:29 (GMT+3) **/

class ArticlesService(
  private val httpClient: HttpClient
) {

  private val country = "us"
  private val category = "general"
  private val apiKey = "654aae3ca7b64f849d7f641f24540269"

  suspend fun getArticles() = httpClient
    .get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
    .body<ArticlesResponse>()
    .articles


}
package my.mvi.dailypulse.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import my.mvi.dailypulse.utils.ApiKeys

class ArticlesService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = ApiKeys.apiKey

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}
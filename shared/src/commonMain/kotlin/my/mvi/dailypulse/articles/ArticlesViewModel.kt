package my.mvi.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import my.mvi.dailypulse.BaseViewModel

/** Created by Sergei Kolinichenko on 10.09.2024 at 20:07 (GMT+3) **/

class ArticlesViewModel: BaseViewModel() {

  private val _articlesState = MutableStateFlow(ArticleState())
  val articlesState: StateFlow<ArticleState> = _articlesState
  private val useCase: ArticlesUseCase

  init {

    val httpClient = HttpClient{
      install(ContentNegotiation){
        json( Json {
          prettyPrint = true
          isLenient = true
          ignoreUnknownKeys = true
        })
      }
    }

    val service = ArticlesService(httpClient)

    useCase = ArticlesUseCase(service)

    getArticles()
  }

  private fun getArticles() {
    scope.launch {

      _articlesState.value = ArticleState(loading = true)

      val fetched = useCase.getArticles()

      _articlesState.value = ArticleState(
        loading = false,
        articles = fetched
      )
    }
  }
}
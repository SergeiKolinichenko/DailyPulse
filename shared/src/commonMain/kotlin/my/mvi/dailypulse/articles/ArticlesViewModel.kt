package my.mvi.dailypulse.articles

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.mvi.dailypulse.BaseViewModel

/** Created by Sergei Kolinichenko on 10.09.2024 at 20:07 (GMT+3) **/

class ArticlesViewModel: BaseViewModel() {

  private val _articlesState = MutableStateFlow<ArticleState>(ArticleState())
  val articlesState: StateFlow<ArticleState> = _articlesState

  init {
    getArticles()
  }

  private fun getArticles() {
    scope.launch {
      _articlesState.value = ArticleState(loading = true)
      delay(500)
      val fetched = fetchArticles()
      _articlesState.value = ArticleState(
        loading = false,
        articles = fetched
      )
    }
  }

  private suspend fun fetchArticles(): List<Article> = mockArticles

  private val mockArticles = listOf(
    Article(
      "Stock market today: Live updates - CNBC",
      "Futures were higher in premarket trading as Wall Street tried to regain its footing.",
      "2023-11-09",
      "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
    ),
    Article(
      "Best iPhone Deals (2023): Carrier Deals, Unlocked iPhones",
      "Apple's smartphones rarely go on sale, but if you’re looking to upgrade (or you're gift shopping), here are a few cost-saving options.",
      "2023-11-09",
      "https://media.wired.com/photos/622aa5c8cca6acf55fb70b57/191:100/w_1280,c_limit/iPhone-13-Pro-Colors-SOURCE-Apple-Gear.jpg",
    ),
    Article(
      "Samsung details ‘Galaxy AI’ and a feature that can translate phone calls in real time",
      "In a new blog post, Samsung previewed what it calls “a new era of Galaxy AI” coming to its smartphones and detailed a feature that will use artificial intelligence to translate phone calls in real time.",
      "2023-11-09",
      "https://cdn.vox-cdn.com/thumbor/Ocz_QcxUdtaexp1pPTMygaqzbR8=/0x0:2000x1333/1200x628/filters:focal(1000x667:1001x668)/cdn.vox-cdn.com/uploads/chorus_asset/file/24396795/DSC04128_processed.jpg",
    ),
  )
}
package my.mvi.dailypulse.articles.data

import my.mvi.dailypulse.database.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleRaw>  =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) = database.dailyPulseDatabaseQueries.transaction {
        articles.forEach { articleRaw -> insertArticle(articleRaw) }
    }

    fun cleanArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(article: ArticleRaw) =
        database.dailyPulseDatabaseQueries.insertArticle(
            title = article.title,
            desc = article.desc,
            date = article.date,
            imageUrl = article.imageUrl
        )

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ) = ArticleRaw(
        title = title,
        desc = desc,
        date = date,
        imageUrl = imageUrl
    )
}
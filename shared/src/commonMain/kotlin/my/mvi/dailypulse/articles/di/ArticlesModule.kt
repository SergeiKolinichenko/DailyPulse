package my.mvi.dailypulse.articles.di

import my.mvi.dailypulse.articles.ArticlesDataSource
import my.mvi.dailypulse.articles.ArticlesRepository
import my.mvi.dailypulse.articles.ArticlesService
import my.mvi.dailypulse.articles.ArticlesUseCase
import my.mvi.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single { ArticlesDataSource(get()) }
    single { ArticlesRepository(get(), get()) }

}
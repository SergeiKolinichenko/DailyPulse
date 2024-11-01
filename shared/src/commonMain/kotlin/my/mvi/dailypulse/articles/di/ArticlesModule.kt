package my.mvi.dailypulse.articles.di

import my.mvi.dailypulse.articles.data.ArticlesDataSource
import my.mvi.dailypulse.articles.data.ArticlesRepositoryImpl
import my.mvi.dailypulse.articles.data.ArticlesService
import my.mvi.dailypulse.articles.domain.ArticlesRepository
import my.mvi.dailypulse.articles.domain.ArticlesUseCase
import my.mvi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get(), get()) }
}
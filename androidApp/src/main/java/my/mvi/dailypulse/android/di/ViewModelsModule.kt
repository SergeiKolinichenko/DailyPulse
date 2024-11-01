package my.mvi.dailypulse.android.di

import my.mvi.dailypulse.articles.presentation.ArticlesViewModel
import my.mvi.dailypulse.sources.presentation.SourcesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        ArticlesViewModel(get())
        SourcesViewModel(get())
    }
}
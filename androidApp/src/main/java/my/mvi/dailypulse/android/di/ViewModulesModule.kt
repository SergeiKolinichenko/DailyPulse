package my.mvi.dailypulse.android.di

import my.mvi.dailypulse.articles.ArticlesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ArticlesViewModel(get())
    }
}
package my.mvi.dailypulse.di

import my.mvi.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule,
)
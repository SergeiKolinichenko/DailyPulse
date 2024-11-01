package my.mvi.dailypulse.di

import my.mvi.dailypulse.articles.di.articlesModule
import my.mvi.dailypulse.sources.di.sourcesModule

val sharedKoinModules = listOf(
    articlesModule,
    sourcesModule,
    networkModule,
)
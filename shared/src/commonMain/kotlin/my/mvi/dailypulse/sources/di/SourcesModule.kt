package my.mvi.dailypulse.sources.di

import my.mvi.dailypulse.sources.data.SourcesDataSource
import my.mvi.dailypulse.sources.data.SourcesRepositoryImpl
import my.mvi.dailypulse.sources.data.SourcesService
import my.mvi.dailypulse.sources.domain.SourcesRepository
import my.mvi.dailypulse.sources.domain.SourcesUseCase
import my.mvi.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule= module {
    single { SourcesService(get()) }
    single { SourcesUseCase(get()) }
    single { SourcesViewModel(get()) }
    single { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepositoryImpl(get(), get()) }


}
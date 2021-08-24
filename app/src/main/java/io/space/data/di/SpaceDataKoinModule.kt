package io.space.data.di

import io.space.data.api.AstronomyPictureOfTheDayApiRest
import io.space.data.fetcher.RemoteAstronomyPictureOfTheDayFetcher
import io.space.data.network.NasaClientFactory
import io.space.data.network.SafeRequest
import io.space.domain.usecase.AstronomyPictureOfTheDayFetcher
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectSpaceDataKoinModule() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(listOf(serviceModule, networkModule))
}

private val serviceModule = module {
    single<AstronomyPictureOfTheDayFetcher> { RemoteAstronomyPictureOfTheDayFetcher(get(), get()) }
    single { get<NasaClientFactory>().create(AstronomyPictureOfTheDayApiRest::class.java) }
}

private val networkModule = module {
    single { SafeRequest() }
    single { NasaClientFactory() }
}

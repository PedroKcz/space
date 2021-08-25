package io.space

import io.space.domain.entity.AstronomyPicture
import io.space.domain.entity.Status
import io.space.domain.usecase.AstronomyPictureOfTheDayFetcher
import io.space.presentation.SpaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mockedModules = module {
    viewModel { SpaceViewModel(get()) }
    single<AstronomyPictureOfTheDayFetcher> { MockedAstronomyPictureOfTheDayFetcher() }
}

class MockedAstronomyPictureOfTheDayFetcher : AstronomyPictureOfTheDayFetcher {

    override suspend fun fetch() =
        Status.Success(AstronomyPicture("bla", "bla bla", "bla"))
}

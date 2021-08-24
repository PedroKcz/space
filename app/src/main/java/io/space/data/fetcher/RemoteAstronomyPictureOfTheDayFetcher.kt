package io.space.data.fetcher

import io.space.data.api.AstronomyPictureOfTheDayApiRest
import io.space.data.network.SafeRequest
import io.space.domain.usecase.AstronomyPictureOfTheDayFetcher

class RemoteAstronomyPictureOfTheDayFetcher(
    private val safeRequest: SafeRequest,
    private val rest: AstronomyPictureOfTheDayApiRest
) : AstronomyPictureOfTheDayFetcher {

    override suspend fun fetch() = safeRequest { rest.fetchPicOfTheDay().transform() }
}

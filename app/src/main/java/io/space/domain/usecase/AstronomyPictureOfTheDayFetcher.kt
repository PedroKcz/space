package io.space.domain.usecase

import io.space.domain.entity.AstronomyPicture
import io.space.domain.entity.Status

interface AstronomyPictureOfTheDayFetcher {
    suspend fun fetch(): Status<AstronomyPicture>
}

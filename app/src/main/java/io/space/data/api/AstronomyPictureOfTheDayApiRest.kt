package io.space.data.api

import io.space.data.remote.RemoteAstronomyPicture
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyPictureOfTheDayApiRest {

    @GET("/planetary/apod")
    suspend fun fetchPicOfTheDay(
        @Query("thumbs") thumbs: Boolean = true
    ): RemoteAstronomyPicture
}

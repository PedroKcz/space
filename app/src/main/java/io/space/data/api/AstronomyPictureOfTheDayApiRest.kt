package io.space.data.api

import io.space.data.remote.RemoteAstronomyPicture
import retrofit2.http.GET

interface AstronomyPictureOfTheDayApiRest {

    @GET("/planetary/apod")
    suspend fun fetchPicOfTheDay(): RemoteAstronomyPicture
}

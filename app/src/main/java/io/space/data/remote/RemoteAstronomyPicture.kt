package io.space.data.remote

import com.squareup.moshi.JsonClass
import io.space.domain.entity.AstronomyPicture

@JsonClass(generateAdapter = true)
data class RemoteAstronomyPicture(
    val title: String?,
    val description: String?,
    val url: String,
) {
    fun transform() = AstronomyPicture(
        title = title ?: "",
        description = description ?: "",
        url = url
    )
}

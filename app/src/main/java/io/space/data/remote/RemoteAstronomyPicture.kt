package io.space.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.space.domain.entity.AstronomyPicture

@JsonClass(generateAdapter = true)
data class RemoteAstronomyPicture(
    @field:Json(name = "title") var title: String?,
    @field:Json(name = "explanation") var description: String?,
    @field:Json(name = "hdurl") var hdurl: String?,
    @field:Json(name = "thumbnail_url") var thumbnailUrl: String?,
) {
    fun transform() = AstronomyPicture(
        title = title ?: "",
        description = description ?: "",
        url = hdurl ?: thumbnailUrl ?: ""
    )
}

package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Holds the data of a single GIF.
 */
@JsonClass(generateAdapter = true)
data class Gif(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "embed_url") val embedUrl: String,
)
package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Contains the data of a single GIPHY REST API response.
 *
 * @see Gif
 * @see GiphyMeta
 */
@JsonClass(generateAdapter = true)
data class GiphyResponse(
    @field:Json(name = "data") val data: List<Gif>,
    @field:Json(name = "meta") val meta: GiphyMeta,
)
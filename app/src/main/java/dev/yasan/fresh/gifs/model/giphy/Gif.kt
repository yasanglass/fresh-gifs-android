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
    @field:Json(name = "images") val images: GiphyImages,
) {

    /**
     * Checks if the object is a valid GIF.
     */
    fun isValid() = id.isNotBlank() && title.isNotBlank() && images.previewGif.url != null

}
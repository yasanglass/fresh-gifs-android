package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif

/**
 * Holds the data of a single GIF.
 */
@JsonClass(generateAdapter = true)
data class Gif(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "images") val images: GiphyImages,
    @field:Json(name = "type") val type: String = EXPECTED_TYPE,
) {

    companion object {
        private const val EXPECTED_TYPE = "gif"
    }

    /**
     * Checks if the object is a valid GIF.
     */
    fun isValid() =
        type == EXPECTED_TYPE && id.isNotBlank() && title.isNotBlank() && images.previewGif.url != null && images.previewGif.height != null && images.previewGif.width != null

    fun flatten() = FlatGif(
        id = id,
        title = title,
        url = images.previewGif.url ?: "",
        height = images.previewGif.height?.toIntOrNull() ?: 1,
        width = images.previewGif.width?.toIntOrNull() ?: 1,
    )

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Gif
        return id == other.id
    }

}
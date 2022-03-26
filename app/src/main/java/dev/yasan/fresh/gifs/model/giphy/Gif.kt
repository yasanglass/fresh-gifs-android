package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.WebHelper

/**
 * Holds the data of a single GIF.
 *
 * @see FlatGif
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
    fun isValid() = type == EXPECTED_TYPE
            && id.isNotBlank()
            && title.isNotBlank()
            && images.previewGif.url != null
            && images.previewGif.height?.toIntOrNull() != null
            && images.previewGif.width?.toIntOrNull() != null
            && WebHelper.isStringURL(images.previewGif.url)

    /**
     * Converts the object to a [FlatGif].
     */
    fun flatten() = FlatGif(
        id = id,
        title = title,
        url = images.previewGif.url ?: "",
        height = images.previewGif.height?.toIntOrNull() ?: 1,
        width = images.previewGif.width?.toIntOrNull() ?: 1,
    )

}
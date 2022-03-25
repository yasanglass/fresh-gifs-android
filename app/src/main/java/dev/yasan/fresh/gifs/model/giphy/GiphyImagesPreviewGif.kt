package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyImagesPreviewGif(
    @field:Json(name = "height") val height: String?,
    @field:Json(name = "width") val width: String?,
    @field:Json(name = "url") val url: String?,
) {

    val aspectRatio = (width?.toFloatOrNull() ?: 1f) / (height?.toFloatOrNull() ?: 1f)

}
package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyImages(
    @field:Json(name = "preview_gif") val previewGif: GiphyImagesPreviewGif,
)


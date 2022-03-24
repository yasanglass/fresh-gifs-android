package dev.yasan.fresh.gifs.model.giphy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Contains basic information regarding the [GiphyResponse] and its status.
 */
@JsonClass(generateAdapter = true)
data class GiphyMeta(
    @field:Json(name = "msg") val message: String,
    @field:Json(name = "status") val status: Int,
    @field:Json(name = "response_id") val responseId: String,
)
package dev.yasan.fresh.gifs.model.freshgifs

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yasan.fresh.gifs.data.source.database.FavoritesDatabase
import dev.yasan.fresh.gifs.model.giphy.Gif
import dev.yasan.fresh.gifs.model.giphy.GiphyImages
import dev.yasan.fresh.gifs.model.giphy.GiphyImagesPreviewGif

/**
 * A simplified version of [Gif].
 *
 * @see Gif
 * @see FavoritesDatabase
 */
@Entity(tableName = "favorite_gifs")
data class FlatGif(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val url: String,
    val height: Int,
    val width: Int,
    val date: Long = System.currentTimeMillis()
) {

    /**
     * Converts the object to a [Gif].
     */
    fun toGif() = Gif(
        id = id,
        title = title,
        images = GiphyImages(
            previewGif = GiphyImagesPreviewGif(
                url = url,
                height = height.toString(),
                width = width.toString()
            )
        )
    )

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as FlatGif
        return id == other.id
    }

}
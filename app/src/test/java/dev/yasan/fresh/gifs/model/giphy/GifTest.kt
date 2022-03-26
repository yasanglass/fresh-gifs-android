package dev.yasan.fresh.gifs.model.giphy

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class GifTest {

    private lateinit var validGiphyImagesPreviewGif: GiphyImagesPreviewGif
    private lateinit var validGiphyImages: GiphyImages
    private lateinit var validGif: Gif

    @Before
    fun setUp() {

        validGiphyImagesPreviewGif = GiphyImagesPreviewGif(
            height = "100",
            width = "100",
            url = "https://media1.giphy.com/media/l2Jehbz2HdLnTQSFa/giphy-preview.mp4?cid=3974a4b8wd6volezjgk0fsdrjxlgqnysd6v9ysfzcuve37xb&rid=giphy-preview.mp4&ct=g"
        )

        validGiphyImages = GiphyImages(
            previewGif = validGiphyImagesPreviewGif
        )

        validGif = Gif(
            id = "id",
            title = "You're Hired",
            images = validGiphyImages
        )

    }

    @Test
    fun valid_gif_passes_validation() {
        Truth.assertThat(validGif.isValid()).isTrue()
    }

    @Test
    fun blank_gif_id_fails_validation() {
        val gif = validGif.copy(id = "")
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun blank_gif_title_fails_validation() {
        val gif = validGif.copy(title = "")
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun null_gif_height_fails_validation() {
        val gif = validGif.copy(
            images = validGiphyImages.copy(previewGif = validGiphyImagesPreviewGif.copy(height = null))
        )
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun invalid_gif_height_fails_validation() {
        val gif = validGif.copy(
            images = validGiphyImages.copy(previewGif = validGiphyImagesPreviewGif.copy(height = "Not A Number"))
        )
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun null_gif_width_fails_validation() {
        val gif = validGif.copy(
            images = validGiphyImages.copy(previewGif = validGiphyImagesPreviewGif.copy(width = null))
        )
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun invalid_gif_width_fails_validation() {
        val gif = validGif.copy(
            images = validGiphyImages.copy(previewGif = validGiphyImagesPreviewGif.copy(width = "Not A Number"))
        )
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun null_gif_url_fails_validation() {
        val gif = validGif.copy(
            images = validGiphyImages.copy(previewGif = validGiphyImagesPreviewGif.copy(url = null))
        )
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun invalid_gif_url_fails_validation() {
        val gif = validGif.copy(
            images = validGiphyImages.copy(previewGif = validGiphyImagesPreviewGif.copy(url = "You're Not Hired"))
        )
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun invalid_gif_type_fails_validation() {
        val gif = validGif.copy(type = "video")
        Truth.assertThat(gif.isValid()).isFalse()
    }

    @Test
    fun flattened_gif_content_is_the_same() {
        val flattenedGif = validGif.flatten()
        Truth.assertThat(flattenedGif.id).isEqualTo(validGif.id)
        Truth.assertThat(flattenedGif.title).isEqualTo(validGif.title)
        Truth.assertThat(flattenedGif.url).isEqualTo(validGif.images.previewGif.url)
        Truth.assertThat(flattenedGif.height)
            .isEqualTo(validGif.images.previewGif.height?.toIntOrNull() ?: 1)
        Truth.assertThat(flattenedGif.width)
            .isEqualTo(validGif.images.previewGif.width?.toIntOrNull() ?: 1)
    }

}
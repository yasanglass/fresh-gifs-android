package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.model.freshgifs.GifLoadState
import dev.yasan.fresh.gifs.model.giphy.Gif
import dev.yasan.fresh.gifs.presentation.compose.common.FreshDivider
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

@Composable
fun FavoriteGifItem(modifier: Modifier = Modifier, gif: Gif, favorite: Boolean, onClick: () -> Unit) {

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val loadingState = remember {
        mutableStateOf(GifLoadState.LOADING)
    }

    Column(
        modifier = modifier
            .padding(bottom = grid(2))
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.layer_foreground))
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier
                .placeholder(
                    visible = loadingState.value == GifLoadState.LOADING,
                    highlight = PlaceholderHighlight.fade(highlightColor = colorResource(id = R.color.layer_foreground)),
                    color = colorResource(id = R.color.layer_background),
                )
                .fillMaxWidth()
                .requiredHeight(grid(24)),
            model = gif.images.previewGif.url,
            contentDescription = gif.title,
            imageLoader = imageLoader,
            contentScale = ContentScale.FillBounds,
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Success -> {
                    loadingState.value = GifLoadState.LOADED
                    SubcomposeAsyncImageContent()
                }
                is AsyncImagePainter.State.Error -> {
                    loadingState.value = GifLoadState.FAILED
                    GifItemError()
                }
                else -> {
                    loadingState.value = GifLoadState.LOADING
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            val rowPadding = grid(2)

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = rowPadding)
                    .padding(vertical = rowPadding),
                text = gif.title,
                color = colorResource(id = R.color.text_title),
                maxLines = 1,
                fontFamily = rubikFamily,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier.padding(rowPadding),
                imageVector = if (favorite) MyAppIcons.Favorite else MyAppIcons.FavoriteBorder,
                contentDescription = null,
                tint = colorResource(id = R.color.text_title)
            )

        }

        FreshDivider()

    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GifItemError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.layer_background)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(grid(2)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = MyAppIcons.Warning,
                contentDescription = null,
                tint = colorResource(id = R.color.text_desc)
            )
            Spacer(modifier = Modifier.requiredWidth(grid()))
            Text(
                text = stringResource(R.string.failed_to_load_gif),
                color = colorResource(id = R.color.text_desc),
                fontFamily = rubikFamily,
            )
        }
    }
}
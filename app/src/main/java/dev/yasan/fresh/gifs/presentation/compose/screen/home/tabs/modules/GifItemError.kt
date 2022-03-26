package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites.FavoriteGifItem
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

/**
 * Shown when there is an error loading the gifs.
 *
 * @see GifItem
 * @see FavoriteGifItem
 */
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
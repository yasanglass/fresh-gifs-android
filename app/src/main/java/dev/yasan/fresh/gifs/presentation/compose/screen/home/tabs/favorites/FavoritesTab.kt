package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.EmptyTabContent
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.GifItem
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.TabFooter
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.core.Resource

private const val TAG = "FavoritesTab"

@Composable
fun FavoritesTab(favoriteGifs: List<FlatGif>, onRemoveFromFavorites: (FlatGif) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            AnimatedVisibility(
                visible = favoriteGifs.isEmpty(),
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {

                EmptyTabContent(
                    text = stringResource(id = R.string.no_favorite_gifs),
                    icon = MyAppIcons.FavoriteBorder
                )

            }

        }


        items(favoriteGifs) { flatGif ->

            GifItem(
                gif = flatGif.toGif(),
                favorite = favoriteGifs.contains(flatGif),
            ) {
                onRemoveFromFavorites(flatGif)
            }

        }

        item {

            TabFooter()

        }

    }

}
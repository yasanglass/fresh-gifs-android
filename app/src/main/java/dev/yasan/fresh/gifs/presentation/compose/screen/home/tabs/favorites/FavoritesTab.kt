package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.EmptyTabContent
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.compose.foundation.grid

private const val TAG = "FavoritesTab"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesTab(favoriteGifs: List<FlatGif>, onRemoveFromFavorites: (FlatGif) -> Unit) {

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

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        cells = GridCells.Adaptive(grid(24)),
    ) {

        items(favoriteGifs) { flatGif ->

            FavoriteGifItem(
                modifier = Modifier,
                gif = flatGif,
                favorite = favoriteGifs.contains(flatGif),
            ) {
                onRemoveFromFavorites(flatGif)
            }

        }

    }

}
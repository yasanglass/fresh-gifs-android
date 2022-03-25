package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.GifItem

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

        items(favoriteGifs) { flatGif ->

            GifItem(
                gif = flatGif.toGif(),
                favorite = favoriteGifs.contains(flatGif),
            ) {
                onRemoveFromFavorites(flatGif)
            }

        }

    }
}
package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.GifItem
import dev.yasan.kit.core.Resource

@Composable
fun SearchTab() {

    val searchViewModel: SearchViewModel = hiltViewModel()

    val trendingGifs = searchViewModel.trendingGifs.observeAsState()

    DisposableEffect(searchViewModel) {

        if (trendingGifs.value is Resource.Initial) {
            searchViewModel.loadTrendingGifs()
        }

        onDispose {
        }
    }

    Surface(color = colorResource(id = R.color.layer_midground)) {
        if (trendingGifs.value is Resource.Success) {

            LazyColumn {

                Log.d("TAG", "SearchTab: ${trendingGifs.value?.data?.size}")

                items(
                    items = trendingGifs.value!!.data!!,
                    key = { it.id }
                ) { gif ->
                    GifItem(gif = gif)
                }

            }

        }
    }


}
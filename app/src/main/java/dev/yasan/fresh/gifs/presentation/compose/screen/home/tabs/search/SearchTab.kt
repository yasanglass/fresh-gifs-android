package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.EmptyTabContent
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.ErrorTabContent
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules.GifItem
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily
import dev.yasan.kit.core.Resource

private const val TAG = "SearchTab"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTab(
    favoriteGifs: List<FlatGif>,
    onAddToFavorites: (FlatGif) -> Unit,
    onRemoveFromFavorites: (FlatGif) -> Unit
) {

    Log.d(TAG, "SearchTab: favorites size = ${favoriteGifs.size}")

    val searchViewModel: SearchViewModel = hiltViewModel()

    val trendingGifs = searchViewModel.trendingGifs.observeAsState()
    val queriedGifs = searchViewModel.queriedGifs.observeAsState()
    val queryState = rememberSaveable { mutableStateOf("") }

    if (trendingGifs.value is Resource.Initial) {
        searchViewModel.loadTrendingGifs()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        stickyHeader {

            SearchField(
                value = queryState.value,
                onValueChange = {
                    queryState.value = it
                    searchViewModel.loadQueriedGifs(query = it)
                }
            )

        }

        val noQuery = queryState.value.isBlank()

        val content = if (noQuery) {
            trendingGifs.value
        } else {
            queriedGifs.value
        }

        item {
            AnimatedVisibility(
                visible = noQuery,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = grid(2))
                        .padding(top = grid(2)),
                    text = stringResource(R.string.trending_gifs),
                    fontFamily = rubikFamily,
                    color = colorResource(id = R.color.text_desc),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {

            AnimatedVisibility(
                visible = content is Resource.Success && content.data.isNullOrEmpty(),
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {

                EmptyTabContent(text = stringResource(id = if (noQuery) R.string.no_trending_gifs else R.string.no_gifs_found))

            }

        }

        when (content) {

            is Resource.Success -> {

                val list = content.data ?: emptyList()

                items(
                    items = list,
                    key = { it.id }
                ) { gif ->
                    val favorite = favoriteGifs.contains(gif.flatten())
                    Log.d(TAG, "SearchTab: ${gif.id} is favorite = $favorite")
                    GifItem(
                        modifier = Modifier.animateItemPlacement(),
                        gif = gif,
                        favorite = favorite
                    ) {
                        val flattenGif = gif.flatten()
                        if (favorite) onRemoveFromFavorites(flattenGif)
                        else onAddToFavorites(flattenGif)
                    }
                }
            }

            is Resource.Error -> {

                item {
                    ErrorTabContent(
                        message = stringResource(
                            id = content.messageResourceId ?: R.string.failed_to_load_data
                        )
                    )
                }

            }

            else -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(grid(2))
                            .animateItemPlacement()
                    )
                }
            }

        }

    }

}
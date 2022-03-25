package dev.yasan.fresh.gifs.presentation.compose.screen.home.modules

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import dev.yasan.fresh.gifs.model.freshgifs.HomeTab
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.favorites.FavoritesTab
import dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search.SearchTab

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {

    HorizontalPager(
        modifier = modifier,
        count = HomeTab.values().size,
        state = pagerState,
    ) { page ->
        when (page) {
            HomeTab.SEARCH.ordinal -> {
                SearchTab()
            }
            else -> {
                FavoritesTab()
            }
        }
    }

}
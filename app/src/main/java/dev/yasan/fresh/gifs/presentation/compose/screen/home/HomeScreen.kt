package dev.yasan.fresh.gifs.presentation.compose.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dev.yasan.fresh.gifs.presentation.compose.screen.home.modules.HomeTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {

    val coroutineScope = rememberCoroutineScope()

    val tabs = HomeTab.values()

    Column {

        val pagerState = rememberPagerState()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab.name) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            count = HomeTab.values().size,
            state = pagerState,
        ) { page ->
            when (page) {
                HomeTab.FAVORITES.ordinal -> {
                    Text(
                        text = "Favorites", modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue),
                        color = Color.White
                    )
                }
                else -> {
                    Text(
                        text = "Search", modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red),
                        color = Color.White
                    )
                }
            }
        }

    }

}
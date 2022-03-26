package dev.yasan.fresh.gifs.presentation.compose.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dev.yasan.fresh.gifs.presentation.compose.screen.home.modules.HomePager
import dev.yasan.fresh.gifs.presentation.compose.screen.home.modules.HomeTabRow

/**
 * The apps main and only screen.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {

    Column {

        val pagerState = rememberPagerState()

        HomeTabRow(pagerState = pagerState)

        HomePager(pagerState = pagerState)

    }

}
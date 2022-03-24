package dev.yasan.fresh.gifs.presentation.compose

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.yasan.fresh.gifs.presentation.compose.screen.home.HomeScreen

/**
 * Top-level composable function of Fresh GIFs app.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FreshApp() {

    HomeScreen()

}
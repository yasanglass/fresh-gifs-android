package dev.yasan.fresh.gifs.presentation.compose

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.screen.home.HomeScreen

/**
 * Top-level composable function of Fresh GIFs app.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FreshApp() {

    Surface(color = colorResource(id = R.color.layer_midground)) {
        HomeScreen()
    }

}
package dev.yasan.fresh.gifs.presentation.compose.screen.home.modules

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import dev.yasan.fresh.gifs.model.freshgifs.HomeTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun HomeTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {

    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        HomeTab.values().forEachIndexed { index, tab ->
            Tab(
                text = { Text(text = stringResource(id = tab.titleResourceId)) },
                selected = pagerState.currentPage == index,
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }

}
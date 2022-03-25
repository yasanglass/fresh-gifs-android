package dev.yasan.fresh.gifs.presentation.compose.common

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.theme.dimenBorderWidth

@Composable
fun FreshDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color = colorResource(id = R.color.divider),
        thickness = dimenBorderWidth
    )
}
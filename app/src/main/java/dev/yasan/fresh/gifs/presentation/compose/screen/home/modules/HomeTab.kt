package dev.yasan.fresh.gifs.presentation.compose.screen.home.modules

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons

/**
 * Contains all the data for the Home Tab.
 */
enum class HomeTab(val icon: ImageVector) {
    SEARCH(icon = MyAppIcons.Search),
    FAVORITES(icon = MyAppIcons.Favorite);

    companion object {

        val defaultTab = SEARCH

        fun fromInt(ordinal: Int) = values().getOrNull(ordinal) ?: defaultTab

    }

}
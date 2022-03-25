package dev.yasan.fresh.gifs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.yasan.fresh.gifs.presentation.compose.FreshApp
import dev.yasan.fresh.gifs.presentation.compose.theme.FreshGIFsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreshGIFsTheme {
                FreshApp()
            }
        }
    }

}
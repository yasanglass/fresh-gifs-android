package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.yasan.fresh.gifs.R
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

@Preview(uiMode = UI_MODE_NIGHT_NO, group = "Day")
@Preview(uiMode = UI_MODE_NIGHT_YES, group = "Night")
@Composable
fun TabFooter() {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = grid(2), vertical = grid(12)),
        text = stringResource(R.string.dot),
        fontFamily = rubikFamily,
        color = colorResource(id = R.color.text_desc),
        textAlign = TextAlign.Center
    )

}
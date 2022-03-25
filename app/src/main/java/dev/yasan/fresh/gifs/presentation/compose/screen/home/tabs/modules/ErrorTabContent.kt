package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.preview.string.StringPreviewProvider
import dev.yasan.kit.compose.type.rubikFamily

@Preview(uiMode = UI_MODE_NIGHT_NO, group = "Day")
@Preview(uiMode = UI_MODE_NIGHT_YES, group = "Night")
@Composable
fun ErrorTabContent(@PreviewParameter(StringPreviewProvider::class) message: String) {

    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.layer_background))
            .padding(grid(2))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = MyAppIcons.Warning,
            contentDescription = null,
            tint = colorResource(id = R.color.text_desc)
        )
        Spacer(modifier = Modifier.requiredHeight(grid()))
        Text(
            text = message,
            color = colorResource(id = R.color.text_desc),
            fontFamily = rubikFamily,
            textAlign = TextAlign.Center
        )
    }

}
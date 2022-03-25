package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.modules

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.preview.string.StringPreviewProvider
import dev.yasan.kit.compose.type.rubikFamily

// TODO add retry button

@Preview(uiMode = UI_MODE_NIGHT_NO, group = "Day")
@Preview(uiMode = UI_MODE_NIGHT_YES, group = "Night")
@Composable
fun ErrorTabContent(
    @PreviewParameter(StringPreviewProvider::class) message: String,
    onRetry: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.layer_background))
            .padding(grid(2))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier.requiredSize(grid(6)),
            imageVector = MyAppIcons.Warning,
            contentDescription = null,
            tint = colorResource(id = R.color.text_desc)
        )

        Spacer(modifier = Modifier.requiredHeight(grid(2)))

        Text(
            text = message,
            color = colorResource(id = R.color.text_desc),
            fontFamily = rubikFamily,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.requiredHeight(grid(2)))

        Button(onClick = onRetry) {
            Text(
                modifier = Modifier.padding(grid()),
                text = stringResource(id = R.string.try_again),
                color = Color.White,
                fontFamily = rubikFamily,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.requiredHeight(grid()))

    }

}
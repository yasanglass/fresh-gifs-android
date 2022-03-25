package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.presentation.compose.common.FreshDivider
import dev.yasan.fresh.gifs.presentation.compose.theme.MyAppIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {

        OutlinedTextField(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.layer_foreground)
                )
                .fillMaxWidth()
                .padding(
                    start = grid(2),
                    end = grid(2),
                    top = grid(),
                    bottom = grid(2)
                ),
            value = value,
            onValueChange = {
                if (it.length <= 10) {
                    onValueChange(it)
                }
            },
            textStyle = TextStyle(
                fontFamily = rubikFamily,
                color = colorResource(id = R.color.text_title),
                fontSize = 18.sp
            ),
            label = {
                Text(
                    text = stringResource(id = R.string.search),
                    fontFamily = rubikFamily,
                    color = colorResource(id = R.color.text_title)
                )
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    MyAppIcons.Search,
                    contentDescription = stringResource(id = R.string.search),
                    tint = colorResource(id = R.color.text_title),
                    modifier = Modifier.padding(grid(2))
                )
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ), trailingIcon = {
                AnimatedVisibility(
                    visible = value.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Icon(
                        MyAppIcons.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onValueChange("")
                            }
                            .padding(grid(2))
                    )
                }
            }

        )

        FreshDivider()

    }

}
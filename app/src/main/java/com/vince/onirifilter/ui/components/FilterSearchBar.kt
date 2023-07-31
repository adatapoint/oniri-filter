@file:OptIn(ExperimentalMaterial3Api::class)

package com.vince.onirifilter.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.IconAlpha
import com.vince.onirifilter.ui.theme.SectionBackgroundColor
import com.vince.onirifilter.ui.theme.bodyMedium
import com.vince.onirifilter.utils.PreviewContainer

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(20.dp),
        color = SectionBackgroundColor,
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 44.dp)
                    .wrapContentHeight()
                    .padding(bottom = 2.dp, end = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Transparent,
                    unfocusedBorderColor = Transparent
                ),
                singleLine = true,
                value = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(
                        color = White.copy(alpha = IconAlpha),
                        text = "Search in your dreams...",
                        style = bodyMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Unspecified
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(text)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                })
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewSearchBar() {
    PreviewContainer {
        FilterSearchBar {}
    }
}

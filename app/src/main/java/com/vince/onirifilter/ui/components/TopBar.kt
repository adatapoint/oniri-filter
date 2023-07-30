package com.vince.onirifilter.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun TopBar(
    @DrawableRes backIcon: Int = R.drawable.ic_chevron_left,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(42.dp)
    ) {
        IconButton(onClick = { onBackClick.invoke() }) {
            Icon(
                painter = painterResource(id = backIcon),
                contentDescription = "backPress",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewTopBar() {
    PreviewContainer {
        TopBar {}
    }
}

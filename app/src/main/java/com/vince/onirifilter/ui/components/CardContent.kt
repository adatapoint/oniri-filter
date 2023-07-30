package com.vince.onirifilter.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun CardContent(
    isSelected: Boolean,
    @DrawableRes imageResource: Int,
    text: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            colorFilter = ColorFilter.tint(color = if (isSelected) Color.Black else Color.White.copy(alpha = 0.4f)),
            contentDescription = null
        )
        Text(
            color = if (isSelected) Color.Black else Color.White.copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
            text = text
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewCardContent() {
    PreviewContainer {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CardContent(isSelected = false, imageResource = R.drawable.ic_dream, text = "Dream")
            CardContent(isSelected = true, imageResource = R.drawable.ic_dream, text = "Dream")
        }
    }
}
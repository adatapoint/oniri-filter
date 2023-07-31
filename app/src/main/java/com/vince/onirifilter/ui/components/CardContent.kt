package com.vince.onirifilter.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.WhiteWithAlpha
import com.vince.onirifilter.ui.theme.titleMedium
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
        Icon(
            painter = painterResource(id = imageResource),
            tint = if (isSelected) Black else Unspecified,
            contentDescription = null,
        )
        Text(
            color = if (isSelected) Black else WhiteWithAlpha,
            textAlign = TextAlign.Center,
            text = text,
            style = titleMedium,
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

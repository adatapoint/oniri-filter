package com.vince.onirifilter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.OptionBackgroundColor

@Composable
fun SelectableOption(
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    isSelected: Boolean = false,
    content: @Composable (Boolean) -> Unit,
) {
    Surface(
        modifier = modifier.size(size = size),
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) White else OptionBackgroundColor
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) { content(isSelected) }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewSelectableOption() {

    @Composable
    fun complexContent(isSelected: Boolean): @Composable () -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dream),
                colorFilter = ColorFilter.tint(color = if (isSelected) Black else White.copy(alpha = 0.4f)),
                contentDescription = null
            )
            Text(
                color = if (isSelected) Black else White.copy(alpha = 0.4f),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.dream)
            )
        }
    }

    @Composable
    fun simpleContent(isSelected: Boolean): @Composable () -> Unit = {
        Text(
            color = if (isSelected) Black else White.copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
            text = "3"
        )
    }
    PreviewContainer {
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.width(120.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SelectableOption(
                    size = 48.dp,
                    isSelected = true
                ) { isSelected -> simpleContent(isSelected = isSelected).invoke() }
                SelectableOption(
                    size = 48.dp,
                    isSelected = false
                ) { isSelected -> simpleContent(isSelected = isSelected).invoke() }
            }
            SelectableOption(isSelected = true) { isSelected -> complexContent(isSelected).invoke() }
            SelectableOption(isSelected = false) { isSelected -> complexContent(isSelected).invoke() }
        }
    }
}

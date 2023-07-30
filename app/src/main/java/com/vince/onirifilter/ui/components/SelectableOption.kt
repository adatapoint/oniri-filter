package com.vince.onirifilter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.OptionBackgroundColor
import com.vince.onirifilter.ui.theme.SectionBackgroundColor
import com.vince.onirifilter.utils.PreviewContainer
import com.vince.onirifilter.utils.conditional

@Composable
fun SelectableOption(
    id: Any,
    modifier: Modifier = Modifier,
    size: Dp? = null,
    width: Dp = 120.dp,
    height: Dp = 120.dp,
    isSelected: Boolean = false,
    onClick: (Any) -> Unit,
    content: @Composable (Boolean, Any) -> Unit,
) {
    Surface(
        modifier = modifier
            .conditional(
                condition = size != null,
                ifTrue = { size(size!!) },
                ifFalse = {
                    width(width)
                    height(height)
                }
            )
            .toggleable(
                value = isSelected,
                onValueChange = { onClick.invoke(id) },
            ),
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) White else OptionBackgroundColor
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) { content(isSelected, id) }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewSelectableOption() {

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
                    id = 1,
                    size = 48.dp,
                    isSelected = true,
                    onClick = {}
                ) { isSelected, _ -> simpleContent(isSelected = isSelected).invoke() }
                SelectableOption(
                    id = 2,
                    size = 48.dp,
                    isSelected = false,
                    onClick = {}
                ) { isSelected, _ -> simpleContent(isSelected = isSelected).invoke() }
            }
            SelectableOption(
                id = 2,
                isSelected = true,
                size = 120.dp,
                onClick = {}
            ) { isSelected, _ ->
                CardContent(isSelected, R.drawable.ic_dream, stringResource(id = R.string.dream))
            }
            SelectableOption(
                id = 1,
                isSelected = false,
                size = 120.dp,
                onClick = {}
            ) { isSelected, _ ->
                CardContent(isSelected, R.drawable.ic_dream, stringResource(id = R.string.dream))
            }
        }
    }
}

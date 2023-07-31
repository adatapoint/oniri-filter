package com.vince.onirifilter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.ui.theme.IconAlpha
import com.vince.onirifilter.ui.theme.titleMedium
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    title: String,
    scaleLimits: Pair<String, String>? = null,
    onWhatIsThisClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    SectionContainer(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SectionHeader(sectionTitle = title) { onWhatIsThisClick.invoke() }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 32.dp),
            ) {
                content.invoke()
                scaleLimits?.run {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(color = White.copy(alpha = IconAlpha), text = scaleLimits.first, style = titleMedium)
                        Text(color = White.copy(alpha = IconAlpha), text = scaleLimits.second, style = titleMedium)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewSectionCard() {
    PreviewContainer {
        SectionCard(
            title = "Dream length",
            onWhatIsThisClick = {},
            scaleLimits = Pair("Very short", "Very long"),
            content = {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    SelectableOption(
                        id = 1,
                        size = 48.dp,
                        isSelected = true,
                        onClick = {}
                    ) { isSelected, _ ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = IconAlpha),
                            textAlign = TextAlign.Center,
                            text = "1"
                        )
                    }
                    SelectableOption(
                        id = 2,
                        size = 48.dp,
                        isSelected = false,
                        onClick = {}
                    ) { isSelected, _ ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = IconAlpha),
                            textAlign = TextAlign.Center,
                            text = "2"
                        )
                    }
                    SelectableOption(
                        id = 1,
                        size = 48.dp,
                        isSelected = true,
                        onClick = {}
                    ) { isSelected, _ ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = IconAlpha),
                            textAlign = TextAlign.Center,
                            text = "3"
                        )
                    }
                    SelectableOption(
                        id = 1,
                        size = 48.dp,
                        isSelected = false,
                        onClick = {}
                    ) { isSelected, _ ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = IconAlpha),
                            textAlign = TextAlign.Center,
                            text = "4"
                        )
                    }
                    SelectableOption(
                        id = 1,
                        size = 48.dp,
                        isSelected = true,
                        onClick = {}
                    ) { isSelected, _ ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = IconAlpha),
                            textAlign = TextAlign.Center,
                            text = "5"
                        )
                    }
                }
            }
        )
    }
}

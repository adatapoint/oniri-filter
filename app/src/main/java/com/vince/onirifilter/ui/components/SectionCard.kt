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
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
    scaleLimits: Pair<String, String>,
    onHelpClick: () -> Unit,
) {
    SectionContainer(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SectionHeader(sectionTitle = title) { onHelpClick.invoke() }
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(horizontal = 32.dp)) {
                content.invoke()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(color = White.copy(alpha = 0.4f), text = scaleLimits.first)
                    Text(color = White.copy(alpha = 0.4f), text = scaleLimits.second)
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
            onHelpClick = {},
            scaleLimits = Pair("Very short", "Very long"),
            content = {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    SelectableOption(
                        width = 48.dp,
                        height = 48.dp,
                        isSelected = true
                    ) { isSelected ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = 0.4f),
                            textAlign = TextAlign.Center,
                            text = "1"
                        )
                    }
                    SelectableOption(
                        width = 48.dp,
                        height = 48.dp,
                        isSelected = false
                    ) { isSelected ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = 0.4f),
                            textAlign = TextAlign.Center,
                            text = "2"
                        )
                    }
                    SelectableOption(
                        width = 48.dp,
                        height = 48.dp,
                        isSelected = true
                    ) { isSelected ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = 0.4f),
                            textAlign = TextAlign.Center,
                            text = "3"
                        )
                    }
                    SelectableOption(
                        width = 48.dp,
                        height = 48.dp,
                        isSelected = false
                    ) { isSelected ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = 0.4f),
                            textAlign = TextAlign.Center,
                            text = "4"
                        )
                    }
                    SelectableOption(
                        width = 48.dp,
                        height = 48.dp,
                        isSelected = true
                    ) { isSelected ->
                        Text(
                            color = if (isSelected) Color.Black else White.copy(alpha = 0.4f),
                            textAlign = TextAlign.Center,
                            text = "5"
                        )
                    }
                }
            }
        )
    }
}

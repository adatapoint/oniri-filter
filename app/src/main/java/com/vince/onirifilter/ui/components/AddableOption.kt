package com.vince.onirifilter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.ui.theme.OptionBackground
import com.vince.onirifilter.ui.theme.titleMedium
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun AddableOption(
    title: String,
    buttonText: String,
    onWhatIsThisClick: () -> Unit,
    onClick: () -> Unit
) {
    SectionContainer(
        header = {
            SectionHeader(sectionTitle = title) {
                onWhatIsThisClick.invoke()
            }
        }
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = OptionBackground)
                .padding(horizontal = 8.dp)
                .clickable { onClick.invoke() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) { Text(text = buttonText, color = Color.White, style = titleMedium) }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewPeriodSelector() {
    PreviewContainer {
        AddableOption(
            title = "Emotions",
            buttonText = "+ Add emotions",
            onClick = {},
            onWhatIsThisClick = {}
        )
    }
}

package com.vince.onirifilter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vince.onirifilter.R
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    onHelpClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(color = Color.White.copy(alpha = 0.4f), text = sectionTitle)
        Image(
            modifier = Modifier.clickable { onHelpClick.invoke() },
            painter = painterResource(id = R.drawable.ic_question),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewPeriodSelector() {
    PreviewContainer {
        SectionHeader(sectionTitle = "Type") {}
    }
}

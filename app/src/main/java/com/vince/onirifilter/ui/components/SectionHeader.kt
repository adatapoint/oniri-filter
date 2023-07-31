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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.Grey
import com.vince.onirifilter.ui.theme.titleMedium
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    onWhatIsThisClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(color = Grey, text = sectionTitle, style = titleMedium)
        Image(
            modifier = Modifier.clickable { onWhatIsThisClick.invoke() },
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

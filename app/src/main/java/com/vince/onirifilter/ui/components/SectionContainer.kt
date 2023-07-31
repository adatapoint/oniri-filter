package com.vince.onirifilter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.ui.theme.SectionBackgroundColor
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun SectionContainer(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        color = SectionBackgroundColor,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            header?.invoke()
            content.invoke()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewSectionContainer() {
    PreviewContainer {
        SectionContainer {
            PeriodSelector(
                title = "Period",
                initialDate = "December 18th, 2019",
                finalDate = "January 9th, 2020",
                onWhatIsThisClick = {},
                onPickDateClick = {}
            )
        }
    }
}

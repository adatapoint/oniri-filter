package com.vince.onirifilter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.BackgroundColor
import com.vince.onirifilter.ui.theme.OptionBackgroundColor
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun PeriodSelector(
    modifier: Modifier = Modifier,
    title: String,
    onHelpClick: () -> Unit,
    initialDate: String,
    finalDate: String,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        color = OptionBackgroundColor
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SectionHeader(sectionTitle = title) { onHelpClick.invoke() }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(modifier = Modifier.fillMaxWidth(0.2f), text = "From", color = Color.White)
                DateText(modifier = Modifier.fillMaxWidth(), text = initialDate)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier.fillMaxWidth(0.2f), text = "To", color = Color.White)
                DateText(modifier = Modifier.fillMaxWidth(), text = finalDate)
            }
        }
    }
}

@Composable
fun DateText(modifier: Modifier = Modifier, text: String) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = BackgroundColor)
            .padding(vertical = 16.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null)
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewPeriodSelector() {
    PreviewContainer {
        PeriodSelector(
            title = "Period",
            initialDate = "December 18th, 2019",
            finalDate = "January 9th, 2020",
            onHelpClick = {}
        )
    }
}

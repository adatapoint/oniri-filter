package com.vince.onirifilter.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.ui.components.PeriodSelector
import com.vince.onirifilter.ui.components.DreamsSearchBar
import com.vince.onirifilter.utils.PreviewContainer

@Composable
fun MainScreen(
    onWhatIsThisClick: () -> Unit,
    onSearch: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        DreamsSearchBar(onSearch = { query -> onSearch.invoke(query) })
        PeriodSelector(
            title = "Period",
            onWhatIsThisClick = { onWhatIsThisClick.invoke() },
            initialDate = "December 18th, 2019",
            finalDate = "January 9th, 2020"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141840)
@Composable
private fun PreviewMainScreen() {
    PreviewContainer {
        MainScreen(
            onWhatIsThisClick = {},
            onSearch = {}
        )
    }
}

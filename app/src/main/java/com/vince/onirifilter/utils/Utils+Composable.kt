package com.vince.onirifilter.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PreviewContainer(content: @Composable () -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        content()
    }
}

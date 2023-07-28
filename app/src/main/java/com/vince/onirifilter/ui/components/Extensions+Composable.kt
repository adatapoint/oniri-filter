package com.vince.onirifilter.ui.components

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

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier =
    when {
        condition -> then(ifTrue(this))
        ifFalse != null -> then(ifFalse(this))
        else -> this
    }
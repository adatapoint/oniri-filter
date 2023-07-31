package com.vince.onirifilter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vince.onirifilter.R

val Typography = Typography(
    labelLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    )
)

val bodyMedium: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        fontFamily = oniriFonts
    )

val titleLarge: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        fontFamily = oniriFonts
    )

val titleMedium: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        fontFamily = oniriFonts
    )


val oniriFonts = FontFamily(
    Font(R.font.sourcesanspro_bold, weight = FontWeight.Bold),
    Font(R.font.sourcesanspro_regular, weight = FontWeight.Normal)
)

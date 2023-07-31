package com.vince.onirifilter.domain.model

import androidx.annotation.DrawableRes

data class DreamType(
    val id: Int,
    val type: String,
    @DrawableRes val icon: Int
)

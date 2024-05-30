package com.app.heemcream.data


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color



@Immutable
data class DiscountData(
    val icon: Int,
    val name: String,
    val background: Color,
    val count: String,
    val color: Color,
    val subColor: Color
)

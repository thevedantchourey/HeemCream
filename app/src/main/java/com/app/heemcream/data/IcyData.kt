package com.app.heemcream.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


@Immutable
data class IcyData(
    val icon: Int,
    val name: String,
    val background: Color,
    val owner: String,
    val card: Color,
    val price: Double,
    val ingredients: List<String>
)

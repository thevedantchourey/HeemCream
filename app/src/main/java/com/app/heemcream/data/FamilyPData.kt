package com.app.heemcream.data


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color



@Immutable
data class FamilyPData(
    val icon: Int,
    val title: String,
    val background: Color,
    val subtitle: String,
    val color: Color,
    val subColor: Color,
    val price: Double,
    val ingredients: List<String>
)

package com.app.heemcream.data


import androidx.compose.runtime.Immutable


@Immutable
data class CartItems(
    val icon: Int,
    val title: String,
    val subtitle: String
)

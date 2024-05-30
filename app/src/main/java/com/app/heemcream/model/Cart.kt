package com.app.heemcream.model

import androidx.compose.runtime.Immutable

@Immutable
data class Cart(
    val icon: Int? = null,
    var title: String? = null,
    val subtitle: String? = null,
    var quantity: Int? = null,
    val price: Double? = null,
    val ingredients: List<String>? = null
)
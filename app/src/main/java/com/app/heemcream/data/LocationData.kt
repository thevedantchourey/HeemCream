package com.app.heemcream.data

import androidx.compose.runtime.Immutable


@Immutable
data class LocationData(
    val area: String,
    val city: String,
    val country: String
)

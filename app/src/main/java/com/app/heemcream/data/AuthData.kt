package com.app.heemcream.data

import com.app.heemcream.model.Cart

data class AuthData(
    val user: String? = null,
    val pass: String? = null,
    val location: String? = null,
    val ingredients:  MutableList<Cart>? = null
)

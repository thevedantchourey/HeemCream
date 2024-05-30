package com.app.heemcream.model


sealed class DataState {
    class Success(val data: MutableList<Cart>): DataState()
    data object Failure : DataState()
    data object Loading: DataState()
    data object Empty: DataState()
}
package com.app.heemcream.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val selected: ImageVector,
    val route: String
)

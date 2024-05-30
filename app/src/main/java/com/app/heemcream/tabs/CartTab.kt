package com.app.heemcream.tabs


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.app.heemcream.screens.CartScreen


object CartTab: Tab {
    private fun readResolve(): Any = CartTab
    override val options: TabOptions
        @Composable
        get()  {
            val title = "Cart"
            val icon = rememberVectorPainter(Icons.Outlined.ShoppingCart)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = CartScreen()){
            SlideTransition(navigator = it)
        }
    }

}
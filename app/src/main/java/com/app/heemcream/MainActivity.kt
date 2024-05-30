package com.app.heemcream



import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.app.heemcream.tabs.CartTab
import com.app.heemcream.tabs.HistoryTab
import com.app.heemcream.tabs.HomeTab
import com.app.heemcream.tabs.UserTab
import com.app.heemcream.ui.theme.HeemCreamTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController



class MainActivity : ComponentActivity() {

    companion object {
        lateinit var user: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        user = SaveSharedPreference.getUserName(this)
        super.onCreate(savedInstanceState)

        if (SaveSharedPreference.getUserName(this).isEmpty()) {
            val intent = Intent(this, GateWayActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        setContent {
            Initialize()
        }
    }
}

    @Composable
    fun Initialize() {
        HeemCreamTheme {
            SetBarColor(color = Color.White)
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                TabNavigator(tab = HomeTab) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            NavigationBar(
                                containerColor = Color(0xfff5f7fa)
                            ) {
                                TabNavigationItem(tab = HomeTab, selected = Icons.Rounded.Home)
                                TabNavigationItem(
                                    tab = CartTab,
                                    selected = Icons.Rounded.ShoppingCart
                                )
                                TabNavigationItem(
                                    tab = HistoryTab,
                                    selected = Icons.Rounded.DateRange
                                )
                                TabNavigationItem(
                                    tab = UserTab,
                                    selected = Icons.Rounded.AccountCircle
                                )
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .wrapContentSize()
                                .background(Color.White)
                        ) {
                            CurrentTab()
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }


    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab, selected: ImageVector) {
        val tabNavigator = LocalTabNavigator.current
        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = {
                tabNavigator.current = tab
            },
            icon = {
                if (tabNavigator.current == tab) {
                    Icon( imageVector = selected, contentDescription = tab.options.title, tint = MaterialTheme.colorScheme.onBackground)
                } else {
                    tab.options.icon?.let {
                        Icon(painter = it, contentDescription = tab.options.title,tint = Color.DarkGray)
                    }
                }
            },
            label = {
                Text(text = tab.options.title)
            },
            colors = NavigationBarItemDefaults.colors(selectedTextColor = Color.DarkGray, unselectedTextColor = Color.Gray)
        )
    }


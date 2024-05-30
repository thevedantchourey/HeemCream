package com.app.heemcream.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.app.heemcream.SetBarColor
import com.app.heemcream.components.FrontBottom
import com.app.heemcream.components.FrontCenter
import com.app.heemcream.components.FrontHead



class FrontScreen : Screen{
    @Preview
    @Composable
    override fun Content() {
        SetBarColor(color = Color.White)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            FrontHead()
            Spacer(modifier = Modifier.background(Color(0xffE6E5E5)).fillMaxWidth().height(1.dp))
            Spacer(modifier = Modifier.height(120.dp))
            FrontCenter()
            Spacer(modifier = Modifier.height(120.dp))
            FrontBottom()
        }
    }
}
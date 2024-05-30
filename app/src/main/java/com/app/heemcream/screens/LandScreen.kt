package com.app.heemcream.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.app.heemcream.SetBarColor
import com.app.heemcream.components.DiscountSection
import com.app.heemcream.components.FamilySection
import com.app.heemcream.components.IcyDelights
import com.app.heemcream.components.LocationCenter




class LandScreen : Screen {
    @Composable
    override fun Content() {
        SetBarColor(color = Color.White)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White).verticalScroll(rememberScrollState(),true)
        ) {
            LocationCenter()
            DiscountSection()
            Spacer(modifier = Modifier.height(10.dp))
            IcyDelights()
            FamilySection()
        }
    }
}
package com.app.heemcream.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.app.heemcream.R
import com.app.heemcream.components.FrontHead
import com.app.heemcream.components.SignUpBottom
import com.app.heemcream.components.SignUpCenter
import com.app.heemcream.ui.theme.BlueStart


class SignUpScreen: Screen {
    @Preview
    @Composable
    override fun Content() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            FrontHead()
            Spacer(modifier = Modifier
                .background(Color(0xffE6E5E5))
                .fillMaxWidth()
                .height(1.dp))
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, top = 50.dp, bottom = 2.dp),
                text = "Create a new account with ease",
                color = BlueStart,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                style = TextStyle(lineHeight = 22.sp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SignUpCenter()
            Spacer(modifier = Modifier.height(120.dp))
            SignUpBottom()
        }
    }
}
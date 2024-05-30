package com.app.heemcream.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.R
import com.app.heemcream.SetBarColor
import com.app.heemcream.ui.theme.PurpleStart



class OrderScreen: Screen {

    @Preview
    @Composable
    override fun Content() {
        SetBarColor(Color.White)
//        val viewModel: CartViewModel = viewModel()
        val navigator =  LocalNavigator.current

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Card(
                    modifier = Modifier
                        .height(90.dp)
                        .width(230.dp)
                        .padding(top = 20.dp, end = 180.dp, bottom = 20.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color(0xfff5f7fa)),
                    onClick = { navigator?.pop() }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 15.dp),
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = "dust",
                        tint = Color.DarkGray
                    )
                }
                Text(
                    text = "Order",
                    fontSize = 32.sp,
                    color = PurpleStart,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.true_dream)),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 20.dp, end = 20.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .background(Color(0xffE6E5E5))
                    .fillMaxWidth()
                    .height(1.dp)
            )
//            SetData(viewModel)
            Text(
                text = "You have bought nothing yet",
                fontSize = 24.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.rubik_regular)),
                modifier = Modifier
                    .padding(top = 300.dp)
            )
        }
    }
}

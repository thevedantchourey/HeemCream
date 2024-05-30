package com.app.heemcream.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.heemcream.R
import com.app.heemcream.ui.theme.RedStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowStart



@Preview
@Composable
fun FrontCenter(){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(360.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 18.dp)
            .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp))
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(YellowEnd).fillMaxSize()
                .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp))
        ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(bottom = 60.dp, start = 16.dp, end = 16.dp),
                    text = "“There’s no such thing as too much ice cream.”",
                    color = YellowStart,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    style = TextStyle(lineHeight = 30.sp),
                )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(82.dp).background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Card(
                    modifier = Modifier
                        .width(82.dp)
                        .height(82.dp)
                        .align(Alignment.CenterStart)
                        .padding(horizontal = 20.dp),
                    colors = CardDefaults.cardColors(Color.Transparent),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {  }
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "dust",
                        tint = RedStart
                    )
                }
                Button(onClick = {},
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffc3cfe8))
                ) {
                    var state by remember{
                        mutableStateOf("Add")
                    }
                    state =  "Added"

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = TextStyle(lineHeight = 8.sp),
                    )
                }
            }
        }
    }
}
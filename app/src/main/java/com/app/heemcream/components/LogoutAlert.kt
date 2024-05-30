package com.app.heemcream.components


import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.heemcream.GateWayActivity
import com.app.heemcream.R
import com.app.heemcream.SaveSharedPreference
import com.app.heemcream.screens.UserScreen
import com.app.heemcream.ui.theme.GreenEnd
import com.app.heemcream.ui.theme.GreenStart
import com.app.heemcream.ui.theme.RedEnd
import com.app.heemcream.ui.theme.RedStart



class LogoutAlert {
    

    @Preview
    @Composable
    fun ShowLogoutAlert() {
        val context = LocalContext.current

        Card(
            modifier = Modifier
                .height(150.dp)
                .width(280.dp),
            colors = CardDefaults.cardColors(Color(0xfff5f7fa)),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                modifier = Modifier
                    .width(160.dp)
                    .align(Alignment.Start)
                    .padding(16.dp),
                text = "You will be logged out.",
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color.DarkGray,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                style = TextStyle(lineHeight = 20.sp)
            )
            Row(
                modifier = Modifier.padding(start = 160.dp, end = 10.dp, top = 22.dp)
            ) {
                Card(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        SaveSharedPreference.clearUserName(context)
                        val intent  = Intent(context, GateWayActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .background(RedEnd)
                            .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
                        text = "Yes",
                        color = RedStart,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 14.sp),
                    )
                }
                Card(
                    modifier = Modifier.padding(top = 10.dp),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        UserScreen.isTouched.value = false
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .background(GreenEnd)
                            .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
                        text = "No",
                        color = GreenStart,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 14.sp)
                    )
                }
            }
        }
    }
}
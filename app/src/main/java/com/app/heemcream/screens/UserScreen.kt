package com.app.heemcream.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.R
import com.app.heemcream.SaveSharedPreference
import com.app.heemcream.SetBarColor
import com.app.heemcream.components.LogoutAlert
import com.app.heemcream.components.UpdateAlert
import com.app.heemcream.ui.theme.OrangeEnd
import com.app.heemcream.ui.theme.OrangeMid
import com.app.heemcream.ui.theme.OrangeStart
import com.app.heemcream.ui.theme.RedStart



class UserScreen: Screen {

    companion object{
        lateinit var isTouched: MutableState<Boolean>
        lateinit var isUpdated: MutableState<Boolean>
    }

    @Preview
    @Composable
    override fun Content() {
        SetBarColor(Color.White)
        val context = LocalContext.current
        val navigator =  LocalNavigator.current

        isTouched = remember { mutableStateOf(false) }
        isUpdated = remember { mutableStateOf(false) }

        if (isTouched.value){
            Dialog(onDismissRequest = { isTouched.value = false }) {
                LogoutAlert().ShowLogoutAlert()
            }
        }

        if (isUpdated.value){
            Dialog(onDismissRequest = { isUpdated.value = false }) {
                UpdateAlert().ShowUpdateAlert()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            Box{
                Text(
                    text = "User",
                    fontSize = 32.sp,
                    color = OrangeStart,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.true_dream)),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 20.dp)
                        .align(Alignment.TopCenter),
                )
                Spacer(
                    modifier = Modifier
                        .background(Color(0xffE6E5E5))
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(1.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 106.dp, bottom = 20.dp, end = 20.dp, start = 20.dp)
                ){
                    Card(
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp),
                        shape = RoundedCornerShape(80.dp),
                    ) {
                        Image(
                            modifier = Modifier
                                .size(80.dp),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            painter = painterResource(id = R.drawable.no_profile),
                            contentDescription = ""
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp, 8.dp),
                            text = SaveSharedPreference.getUserName(context).ifEmpty { "Username" },
                            fontFamily = FontFamily(Font(R.font.rubik_medium)),
                            color = OrangeMid,
                            maxLines = 1,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(lineHeight = 12.sp)
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp, 0.dp),
                            text = SaveSharedPreference.getLocation(context).ifEmpty { "No location set" },
                            fontFamily = FontFamily(Font(R.font.rubik_regular)),
                            color = OrangeEnd,
                            maxLines = 2,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(lineHeight = 12.sp)
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .background(Color(0xffE6E5E5))
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(start = 20.dp, top = 230.dp, end = 20.dp, bottom = 80.dp)
                    .align(Alignment.Center),
            ) {
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.Start)
                        .height(95.dp)
                        .width(420.dp)
                        .padding(bottom = 10.dp, top = 4.dp, end = 4.dp, start = 4.dp),
                    colors = CardDefaults.cardColors( Color(0xffc3cfe2))
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .clip(RoundedCornerShape(14.dp))
                            .height(160.dp)
                            .fillMaxWidth()
                            .clickable { navigator?.push(OrderScreen()) }
                            .padding(10.dp)
                    ){
                        Card(
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.CenterStart),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(Color(0xfff5f7fa))
                        ) {
                            Image(modifier = Modifier
                                .size(60.dp)
                                .padding(10.dp),
                                alignment = Alignment.Center,
                                imageVector = Icons.AutoMirrored.Rounded.List,
                                contentDescription = "order",
                                colorFilter =  ColorFilter.tint(Color(0xffc3cfe2))
                            )
                        }
                        Text(
                            modifier = Modifier
                                .width(180.dp)
                                .align(Alignment.CenterStart)
                                .padding(start = 100.dp),
                            text = "Order",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Color.White,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(lineHeight = 16.sp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.Start)
                        .height(95.dp)
                        .width(420.dp)
                        .clickable {}
                        .padding(bottom = 10.dp, top = 4.dp, end = 4.dp, start = 4.dp),
                    colors = CardDefaults.cardColors(Color(0xffc3cfe2))
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .clip(RoundedCornerShape(14.dp))
                            .height(160.dp)
                            .fillMaxWidth()
                            .clickable { isUpdated.value = true }
                            .padding(10.dp)
                    ){
                        Card(
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.CenterStart),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(Color(0xfff5f7fa))
                        ) {
                            Image(modifier = Modifier
                                .size(60.dp)
                                .padding(14.dp),
                                alignment = Alignment.Center,
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = "order",
                                colorFilter =  ColorFilter.tint(Color(0xffc3cfe2))
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterStart)
                                .padding(start = 100.dp),
                            text = "Edit Information",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Color.White,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(lineHeight = 16.sp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .height(90.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(Color(0xffE6E5E5))
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
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "dust",
                        tint = RedStart
                    )
                }
                Button(
                    onClick = { isTouched.value = true },
                    modifier = Modifier
                        .width(155.dp)
                        .height(60.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffc3cfe8))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "LogOut",
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


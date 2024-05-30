package com.app.heemcream.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.R
import com.app.heemcream.screens.LoginScreen
import com.app.heemcream.ui.theme.LightBlue
import com.app.heemcream.ui.theme.BlueStart



@Preview
@Composable
fun SignUpBottom(){

    val navigator =  LocalNavigator.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.Absolute.Center,
        verticalAlignment = Alignment.Bottom
    ){
        Card(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 16.dp)
                .width(150.dp)
                .height(70.dp),
            colors = CardDefaults.cardColors(containerColor = LightBlue),
            shape = RoundedCornerShape(6.dp),
            onClick = { navigator?.push(LoginScreen()) }
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, top = 6.dp, bottom = 2.dp),
                text = "login",
                color = BlueStart,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
                style = TextStyle(lineHeight = 14.sp),
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 10.dp, top = 6.dp, bottom = 2.dp).align(Alignment.CenterVertically),
            text = "Already have an account?",
            color = BlueStart,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            style = TextStyle(lineHeight = 22.sp)
        )
    }
}

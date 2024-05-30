package com.app.heemcream.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.heemcream.R
import com.app.heemcream.SaveSharedPreference
import com.app.heemcream.ui.theme.GreenMid
import com.app.heemcream.ui.theme.GreenStart




@Preview
@Composable
fun LocationCenter(){


    val context = LocalContext.current


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.Absolute.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Delivery to",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(4.dp),
                    imageVector = Icons.Rounded.LocationOn,
                    tint = GreenMid,
                    contentDescription = SaveSharedPreference.getUserName(context) )
                Text(
                    text = SaveSharedPreference.getLocation(context).ifEmpty { "No location set" },
                    fontSize =  12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Bold,
                    color =  GreenStart
                )
            }


        }


//        Box(
//            modifier = Modifier
//                .size(40.dp)
//                .clip(RoundedCornerShape(15.dp))
//                .background(MaterialTheme.colorScheme.secondaryContainer)
//                .clickable {},
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                imageVector = Icons.Rounded.AccountCircle,
//                contentDescription = "Search",
//                tint= MaterialTheme.colorScheme.onSecondaryContainer,
//            )
//        }
    }

}
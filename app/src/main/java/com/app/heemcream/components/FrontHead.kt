package com.app.heemcream.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.heemcream.R
import com.app.heemcream.data.LocationData
import com.app.heemcream.ui.theme.BlueEnd


val log = {
    LocationData(
        area = "Bangali Square",
        city = "Indore",
        country = "India"
    )
}


@Preview
@Composable
fun FrontHead(){
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
                text = "HeemCream",
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = BlueEnd
            )
            Spacer(modifier = Modifier.height(4.dp))
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
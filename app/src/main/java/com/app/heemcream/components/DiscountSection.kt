package com.app.heemcream.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.heemcream.R
import com.app.heemcream.data.DiscountData
import com.app.heemcream.ui.theme.BrownEnd
import com.app.heemcream.ui.theme.BrownMid
import com.app.heemcream.ui.theme.BrownStart
import com.app.heemcream.ui.theme.PurpleEnd
import com.app.heemcream.ui.theme.PurpleMid
import com.app.heemcream.ui.theme.PurpleStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowMid
import com.app.heemcream.ui.theme.YellowStart


val discList = listOf(

    DiscountData(
        icon = R.drawable.vanilla_ice_cream,
        name = "Vanilla at a Discount",
        background = PurpleEnd,
        count = "-30% for your Order",
        color = PurpleStart,
        subColor = PurpleMid
    ),
    DiscountData(
        icon = R.drawable.cones,
        name = "Cones at a Discount",
        background = YellowEnd,
        count = "-30% on your first order",
        color = YellowStart,
        subColor = YellowMid

    ),
    DiscountData(
        icon = R.drawable.choco,
        name = "Chocolate at a Discount",
        background = BrownEnd,
        count = "-24% for your order",
        color = BrownStart,
        subColor = BrownMid
    )
    
)




@Preview
@Composable
fun DiscountSection(){

    Column {
        Text(
            text = "Discount %",
            fontSize = 24.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp),
        )

        LazyRow {
            items(discList.size){
                DiscountCardItem(it)
            }
        }

    }

}



@Composable
fun DiscountCardItem(
    index:Int
){
    val discount = discList[index]
    var lastPaddingEnd = 0.dp
    if(index == discList.size-1){
        lastPaddingEnd = 14.dp
    }
    Box(modifier = Modifier.padding(start= 16.dp, end = lastPaddingEnd)){
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(discount.background)
                .height(120.dp)
                .width(220.dp)
                .clickable { }
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .height(160.dp)
                .width(220.dp)
                .padding(10.dp)
            ){
                Image(modifier = Modifier.size(80.dp).align(Alignment.BottomEnd),
                    alignment = Alignment.Center,
                    painter = painterResource(id = discount.icon),
                    contentDescription = discount.name
                )
                Text(
                    modifier = Modifier.width(100.dp).align(Alignment.TopStart),
                    text = discount.name,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = discount.color,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 20.sp)
                )
                Text(
                    modifier = Modifier.width(80.dp).align(Alignment.BottomStart),
                    text = discount.count,
                    color = discount.subColor,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 14.sp)
                )
            }

        }
    }

}
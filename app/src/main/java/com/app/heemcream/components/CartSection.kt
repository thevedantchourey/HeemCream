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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.app.heemcream.ui.theme.GreenEnd
import com.app.heemcream.ui.theme.GreenMid
import com.app.heemcream.ui.theme.GreenStart
import com.app.heemcream.ui.theme.OrangeEnd
import com.app.heemcream.ui.theme.OrangeMid
import com.app.heemcream.ui.theme.OrangeStart
import com.app.heemcream.ui.theme.PurpleEnd
import com.app.heemcream.ui.theme.PurpleMid
import com.app.heemcream.ui.theme.PurpleStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowMid
import com.app.heemcream.ui.theme.YellowStart


val cartList = listOf(

    DiscountData(
        icon = R.drawable.moose_choco,
        name = "Favourite Day - Choco",
        background = BrownEnd,
        count = "Moose Tracks Ice Cream",
        color = BrownStart,
        subColor = BrownMid
    ),
    DiscountData(
        icon = R.drawable.moose_vanilla,
        name = "Favourite Day - Vanilla",
        background = YellowEnd,
        count = "Moose Tracks Ice Cream",
        color = YellowStart,
        subColor = YellowMid

    ),
    DiscountData(
        icon = R.drawable.moose_monster,
        name = "Favourite Day - Monster Cookie",
        background = OrangeEnd,
        count = "Moose Tracks Ice Cream",
        color = OrangeStart,
        subColor = OrangeMid
    ),
    DiscountData(
        icon = R.drawable.moose_neapoliton,
        name = "Favourite Day - Neapolitan",
        background = PurpleEnd,
        count = "Moose Tracks Ice Cream",
        color = PurpleStart,
        subColor = PurpleMid
    )

)



@Preview
@Composable
fun CartSection(){
    Column {
        Text(
            text = "Cart",
            fontSize = 24.sp,
            color = GreenMid,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            modifier = Modifier
                .padding(top = 10.dp, bottom = 60.dp)
                .align(Alignment.CenterHorizontally),
        )

        LazyColumn{
            items(cartList.size){
                CartCardItem(it)
            }
        }

    }

}



@Composable
fun CartCardItem(
    index:Int
){
    val discount = cartList[index]
    Box(modifier = Modifier.padding(start= 10.dp, end = 10.dp , bottom = 14.dp)){
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(GreenEnd)
                .height(90.dp)
                .width(400.dp)
                .clickable { }
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .height(160.dp)
                .width(380.dp)
                .padding(2.dp)
            ){
                Image(modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.Center),
                    alignment = Alignment.BottomCenter,
                    painter = painterResource(id = discount.icon),
                    contentDescription = discount.name
                )
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .align(Alignment.TopStart),
                    text = discount.name,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = GreenStart,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 12.sp)
                )
                Text(
                    modifier = Modifier
                        .width(70.dp)
                        .align(Alignment.BottomStart),
                    text = discount.count,
                    color = GreenMid,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 7.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 8.sp)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                ) {
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp)
                            .align(Alignment.BottomStart)
                            .background(GreenMid)
                            .clip(RoundedCornerShape(10.dp))
                    ){
                        Icon(
                            modifier = Modifier.size(10.dp),
                            imageVector = Icons.Sharp.Add,
                            contentDescription = "Add",
                            tint = GreenEnd
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .background(GreenEnd)
                            .padding(start = 10.dp, end = 10.dp, top = 2.dp, bottom = 2.dp),
                        text = 1.toString(),
                        color = GreenStart,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 8.sp)
                    )
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp)
                            .align(Alignment.BottomEnd)
                            .background(GreenMid)
                            .clip(RoundedCornerShape(10.dp))
                    ){
                        Icon(
                            modifier = Modifier.size(10.dp),
                            painter = painterResource(R.drawable.remove),
                            contentDescription = discount.name,
                            tint = GreenEnd
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .width(95.dp).padding(end = 40.dp)
                        .align(Alignment.CenterEnd),
                    text = "$9.99",
                    fontFamily = FontFamily(Font(R.font.rubik_medium)),
                    color = GreenStart,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 12.sp)
                )
            }
        }

        Card(
            modifier = Modifier
                .size(35.dp).align(Alignment.TopEnd).padding(8.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(5.dp)
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.dustbin),
                contentDescription = discount.name,
                tint = GreenStart
            )
        }
    }

}
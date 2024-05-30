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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.screens.DetailedScreen
import com.app.heemcream.R
import com.app.heemcream.data.FamilyPData
import com.app.heemcream.ui.theme.BrownEnd
import com.app.heemcream.ui.theme.BrownMid
import com.app.heemcream.ui.theme.BrownStart
import com.app.heemcream.ui.theme.OrangeEnd
import com.app.heemcream.ui.theme.OrangeMid
import com.app.heemcream.ui.theme.OrangeStart
import com.app.heemcream.ui.theme.PurpleEnd
import com.app.heemcream.ui.theme.PurpleMid
import com.app.heemcream.ui.theme.PurpleStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowMid
import com.app.heemcream.ui.theme.YellowStart



val famList = listOf(
    FamilyPData(
        icon = R.drawable.moose_choco,
        title = "Favourite Day - Choco",
        background = BrownEnd,
        subtitle = "Moose Tracks Ice Cream",
        color = BrownStart,
        subColor = BrownMid,
        price = 3.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    ),
    FamilyPData(
        icon = R.drawable.moose_vanilla,
        title = "Favourite Day - Vanilla",
        background = YellowEnd,
        subtitle = "Moose Tracks Ice Cream",
        color = YellowStart,
        subColor = YellowMid,
        price = 2.79,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    ),
    FamilyPData(
        icon = R.drawable.moose_monster,
        title = "Favourite Day - Monster Cookie",
        background = OrangeEnd,
        subtitle = "Moose Tracks Ice Cream",
        color = OrangeStart,
        subColor = OrangeMid,
        price = 3.99,        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    ),
    FamilyPData(
        icon = R.drawable.moose_neapoliton,
        title = "Favourite Day - Neapolitan",
        background = PurpleEnd,
        subtitle = "Moose Tracks Ice Cream",
        color = PurpleStart,
        subColor = PurpleMid,
        price = 2.79,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    )

)




@Preview
@Composable
fun FamilySection(){

    Column {
        Text(
            text = "Family Packs",
            fontSize = 24.sp,
            color = OrangeMid,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            modifier = Modifier.padding(16.dp),
        )

        LazyRow {
            items(famList.size){
                FamilyCardItem(it)
            }
        }

    }

}



@Composable
fun FamilyCardItem(
    index:Int
){
    val navigator =  LocalNavigator.current
    var selectedItemIndex by rememberSaveable{
        mutableIntStateOf(0)
    }
    val discount = famList[index]
    var lastPaddingEnd = 0.dp
    if(index == famList.size-1){
        lastPaddingEnd = 14.dp
    }
    
    Box(modifier = Modifier
        .padding(start= 16.dp, end = lastPaddingEnd , bottom = 12.dp, top = 6.dp)
    ){
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(discount.background)
                .height(90.dp)
                .width(220.dp)
                .clickable {
                    selectedItemIndex = index
                    navigator?.push(DetailedScreen(icon = famList[selectedItemIndex].icon, title = famList[selectedItemIndex].title, subtitle = famList[selectedItemIndex].subtitle, price = famList[selectedItemIndex].price, ingredients = famList[selectedItemIndex].ingredients))
                }
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .height(160.dp)
                .width(220.dp)
                .padding(10.dp)
            ){
                Image(modifier = Modifier.size(75.dp).align(Alignment.BottomEnd),
                    alignment = Alignment.Center,
                    painter = painterResource(id = discount.icon),
                    contentDescription = discount.title
                )
                Text(
                    modifier = Modifier.width(100.dp).align(Alignment.TopStart),
                    text = discount.title,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = discount.color,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 16.sp)
                )
                Text(
                    modifier = Modifier.width(80.dp).align(Alignment.BottomStart),
                    text = discount.subtitle,
                    color = discount.subColor,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 8.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 10.sp)
                )
            }
        }
    }
}
package com.app.heemcream.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.screens.DetailedScreen
import com.app.heemcream.R
import com.app.heemcream.data.IcyData
import com.app.heemcream.ui.theme.BlueMid
import com.app.heemcream.ui.theme.BrownEnd
import com.app.heemcream.ui.theme.BrownStart
import com.app.heemcream.ui.theme.PurpleEnd
import com.app.heemcream.ui.theme.PurpleMid
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowStart



val delightsListRowFirst = listOf(

    IcyData(
        icon = R.drawable.vanilla,
        name = "Vanilla",
        background = PurpleMid,
        owner = "Kwality Walls",
        card = PurpleEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 cream","\uD83E\uDD5B milk","\uD83C\uDF3C vanilla extract","\uD83C\uDF6A sugar")
    ),
    IcyData(
        icon = R.drawable.frosty,
        name = "Frosty dual-layered",
        background = YellowStart,
        owner = "HeemCream",
        card = YellowEnd,
        price = 9.99,
        ingredients =   listOf("\uD83E\uDD5B Sweetened Condensed Milk","\uD83C\uDF6B  cocoa powder","\uD83C\uDF6B \uD83E\uDD5B chocolate milk","\uD83C\uDF3C vanilla extract","\uD83E\uDE77 food coloring")

    ),
    IcyData(
        icon = R.drawable.chocobar,
        name = "Choco-Bar",
        background = BrownStart,
        owner = "Magnum",
        card = BrownEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 fresh cream","✨ vanilla essence","\uD83E\uDD5B milkmaid","\uD83C\uDF6A chocolate cookies","\uD83C\uDF6B melted dark choco","\uD83E\uDD5A almonds","\uD83E\uDD65 refined oil")
    )

)


val delightsListRowSecond = listOf(

    IcyData(
        icon = R.drawable.strawberry,
        name = "StrawBerry",
        background = PurpleMid,
        owner = "HeemCream",
        card = PurpleEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    ),
    IcyData(
        icon = R.drawable.mango,
        name = "Mango Sweets",
        background = YellowStart,
        owner = "HeemCream",
        card = YellowEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
        ),
    IcyData(
        icon = R.drawable.mint_choco,
        name = "Mint Choco Chips",
        background = BrownStart,
        owner = "HeemCream",
        card = BrownEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    )

)



val delightsListRowThird = listOf(
    IcyData(
        icon = R.drawable.butter,
        name = "Butter Scotch",
        background = PurpleMid,
        owner = "HeemCream",
        card = PurpleEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")

    ),
    IcyData(
        icon = R.drawable.orange,
        name = "Orange Candy",
        background = YellowStart,
        owner = "Kwality Walls",
        card = YellowEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
        ),
    IcyData(
        icon = R.drawable.choco,
        name = "Chocolate at a Discount",
        background = BrownStart,
        owner = "HeemCream",
        card = BrownEnd,
        price = 9.99,
        ingredients =   listOf("\uD83C\uDF66 heavy cream","\uD83C\uDF66 vanilla","\uD83E\uDD5B milk","\uD83C\uDF6A Oreo cookies","\uD83E\uDDE1 food coloring")
    )

)


@Preview
@Composable
fun IcyDelights(){

    Column {
        Text(
            text = "Icy Delights",
            fontSize = 24.sp,
            color = BlueMid,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 10.dp),
        )

        LazyRow {
            items(discList.size){
                DelightsCardItem(it)
            }
        }

    }

}



@Composable
fun DelightsCardItem(
    index:Int
){
    val navigator =  LocalNavigator.current
    var selectedItemIndex by rememberSaveable{
        mutableIntStateOf(0)
    }
    val delightsRowOne = delightsListRowFirst[index]
    val delightsRowTwo = delightsListRowSecond[index]
    val delightsRowThird = delightsListRowThird[index]
    var lastPaddingEnd = 0.dp
    if(index == discList.size-1){
        lastPaddingEnd = 14.dp
    }
    Column {
        Box(
            modifier = Modifier
            .padding(start= 16.dp, end = lastPaddingEnd, top = 10.dp)
        ){
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(delightsRowOne.card)
                    .height(80.dp)
                    .width(220.dp)
                    .clickable { }
                    .padding(10.dp)
                    .clickable {
                        selectedItemIndex = index
                        navigator?.push(DetailedScreen(icon = delightsListRowFirst[selectedItemIndex].icon, title = delightsListRowFirst[selectedItemIndex].name, subtitle = delightsListRowFirst[selectedItemIndex].owner, price = delightsListRowFirst[selectedItemIndex].price,delightsListRowFirst[selectedItemIndex].ingredients))
                    },
                horizontalArrangement = Arrangement.Start,
            ) {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(60.dp)
                    .background(delightsRowOne.background)
                ){
                    Image(modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                        alignment = Alignment.Center,
                        painter = painterResource(id = delightsRowOne.icon),
                        contentDescription = delightsRowOne.name
                    )
                }
                Column {
                    Text(
                        modifier = Modifier
                            .width(140.dp)
                            .padding(18.dp, 8.dp),
                        text = delightsRowOne.name,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                    Text(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(18.dp, 0.dp),
                        text = delightsRowOne.owner,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.DarkGray,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(start= 16.dp, top = 20.dp, end = lastPaddingEnd)

        ){

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(delightsRowOne.card)
                    .height(80.dp)
                    .width(220.dp)
                    .clickable { }
                    .padding(10.dp)
                    .clickable {
                        selectedItemIndex = index
                        navigator?.push(
                            DetailedScreen(icon = delightsListRowSecond[selectedItemIndex].icon, title = delightsListRowSecond[selectedItemIndex].name, subtitle = delightsListRowSecond[selectedItemIndex].owner, price = delightsListRowSecond[selectedItemIndex].price,
                            delightsListRowSecond[selectedItemIndex].ingredients)
                        )
                    },
                horizontalArrangement = Arrangement.Start,
            ) {

                Box(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(60.dp)
                    .background(delightsRowTwo.background)
                ){
                    Image(modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                        alignment = Alignment.Center,
                        painter = painterResource(id = delightsRowTwo.icon),
                        contentDescription = delightsRowTwo.name
                    )
                }
                Column {
                    Text(
                        modifier = Modifier
                            .width(140.dp)
                            .padding(18.dp, 8.dp),
                        text = delightsRowTwo.name,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                    Text(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(18.dp, 0.dp),
                        text = delightsRowTwo.owner,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.DarkGray,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            }
        }
        Box(modifier = Modifier
            .padding(start= 16.dp, top = 20.dp, end = lastPaddingEnd)

        ){

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(delightsRowThird.card)
                    .height(80.dp)
                    .width(220.dp)
                    .clickable { }
                    .padding(10.dp)
                    .clickable {
                        selectedItemIndex = index
                        navigator?.push(DetailedScreen(icon = delightsListRowThird[selectedItemIndex].icon, title = delightsListRowThird[selectedItemIndex].name, subtitle = delightsListRowThird[selectedItemIndex].owner, price = delightsListRowThird[selectedItemIndex].price, delightsListRowThird[selectedItemIndex].ingredients))
                    },
                horizontalArrangement = Arrangement.Start,
            ) {

                Box(modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(60.dp)
                    .background(delightsRowThird.background)
                ){
                    Image(modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                        alignment = Alignment.Center,
                        painter = painterResource(id = delightsRowThird.icon),
                        contentDescription = delightsRowThird.name
                    )
                }
                Column {
                    Text(
                        modifier = Modifier
                            .width(140.dp)
                            .padding(18.dp, 8.dp),
                        text = delightsRowThird.name,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                    Text(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(18.dp, 0.dp),
                        text = delightsRowThird.owner,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.DarkGray,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            }
        }

    }

}
package com.app.heemcream.screens


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.MainActivity
import com.app.heemcream.R
import com.app.heemcream.SetBarColor
import com.app.heemcream.components.DeleteAlert
import com.app.heemcream.model.Cart
import com.app.heemcream.model.CartViewModel
import com.app.heemcream.model.DataState
import com.app.heemcream.ui.theme.GreenEnd
import com.app.heemcream.ui.theme.GreenMid
import com.app.heemcream.ui.theme.GreenStart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class CartScreen: Screen {

    companion object{
        lateinit var isClicked: MutableState<Boolean>
        lateinit var isProcessed: MutableState<Boolean>
        lateinit var selectedItemIndex: MutableState<Int>
    }


    @Composable
    override fun Content() {
        SetBarColor(Color.White)
        val viewModel: CartViewModel = viewModel()

        isProcessed = remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()){
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cart",
                        fontSize = 32.sp,
                        color = GreenMid,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.true_dream)),
                        modifier = Modifier
                            .padding(top = 30.dp, bottom = 20.dp),
                    )
                    Spacer(
                        modifier = Modifier
                            .background(Color(0xffE6E5E5))
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                    SetData(viewModel)
                }
                if (isProcessed.value){
                    Box(
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(Alignment.BottomCenter)
                    ) {
                        Proceed()
                    }
                }
            }
        }
    }



    @Preview
    @Composable
    fun Proceed(){
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(14.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(GreenMid)
        ) {
            Text(
                modifier = Modifier,
                text = "Proceed",
                color = GreenEnd,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = TextStyle(lineHeight = 8.sp)
            )
        }
        Spacer(
            modifier = Modifier
                .background(Color(0xffE6E5E5))
                .fillMaxWidth()
                .height(1.dp)
        )
    }



    @Composable
    fun SetData(viewModel: CartViewModel) {
        isProcessed = remember { mutableStateOf(false) }

        when(val cart = viewModel.response.value){
            is DataState.Loading ->{
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator(
                        color = GreenEnd
                    )
                }
            }
            is DataState.Success -> {
                LazyList(cart.data)
                isProcessed.value = true
            }
            is DataState.Failure -> {
                Text(
                    text = "Failed to load cart items",
                    fontSize = 24.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    modifier = Modifier
                        .padding(top = 280.dp)
                )
            }
            is DataState.Empty -> {
                Text(
                    text = "No item added to the list",
                    fontSize = 24.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    modifier = Modifier
                        .padding(top = 300.dp)
                )
            }
        }
    }


    @Composable
    fun LazyList(cartList: MutableList<Cart>){
        LazyColumn{
            items(cartList){
                CartCardItem(cartList.size,it,cartList)
            }
        }
    }


    @Composable
    fun CartCardItem(
        index: Int,
        items: Cart,
        cartList: MutableList<Cart>
    ) {

        val navigator =  LocalNavigator.current
        isClicked = remember { mutableStateOf(false) }

        val context = LocalContext.current
        val rootDb = FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.user)
        rootDb.keepSynced(true)
        selectedItemIndex = rememberSaveable{ mutableIntStateOf(0) }


        if (isClicked.value){
            Dialog(onDismissRequest = { isClicked.value = false }) {
                DeleteAlert().ShowDeleteAlert(rootDb = rootDb, title = cartList[selectedItemIndex.value].title!!)
            }
        }

        val icon = items.icon
        val title = items.title
        val subtitle = items.subtitle
        val quantity = items.quantity
        val price = items.price
        val ingredients = items.ingredients


        Box(modifier = Modifier
            .padding(14.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(GreenEnd)
                    .height(90.dp)
                    .width(400.dp)
                    .clickable { }
                    .padding(14.dp)
                    .clickable {
                        navigator?.push(
                            DetailedScreen(
                                icon = icon!!,
                                title = title!!,
                                subtitle = subtitle!!,
                                price = price!!,
                                ingredients = ingredients!!
                            )
                        )
                    },
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .height(160.dp)
                        .width(380.dp)
                        .padding(2.dp)
                ) {
                        Image(
                            modifier = Modifier
                                .size(160.dp)
                                .align(Alignment.Center),
                            alignment = Alignment.BottomCenter,
                            painter = painterResource(id = icon!!),
                            contentDescription = title
                        )
                        Text(
                            modifier = Modifier
                                .width(100.dp)
                                .align(Alignment.TopStart),
                            text = title!!,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = GreenStart,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(lineHeight = 12.sp)
                        )
                        Text(
                            modifier = Modifier
                                .width(70.dp)
                                .align(Alignment.BottomStart),
                            text = subtitle!!,
                            color = GreenMid,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 8.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(lineHeight = 8.sp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(3.dp)
                                .size(80.dp)
                        ) {
                            IconButton(
                                onClick = { incQuantity(rootDb,title,context) },
                                modifier = Modifier
                                    .height(16.dp)
                                    .width(16.dp)
                                    .align(Alignment.BottomStart)
                                    .background(GreenMid)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                Icon(
                                    modifier = Modifier.size(10.dp),
                                    imageVector = Icons.Sharp.Add,
                                    contentDescription = "Add",
                                    tint = GreenEnd
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 33.dp)
                                    .background(GreenEnd)
                                    .height(25.dp)
                                    .width(25.dp),
                                text = quantity.toString(),
                                color = GreenStart,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(lineHeight = 8.sp)
                            )
                            IconButton(
                                onClick = { decQuantity(rootDb,title,context) },
                                modifier = Modifier
                                    .height(16.dp)
                                    .width(16.dp)
                                    .align(Alignment.BottomEnd)
                                    .background(GreenMid)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                Icon(
                                    modifier = Modifier.size(10.dp),
                                    painter = painterResource(R.drawable.remove),
                                    contentDescription = "remove",
                                    tint = GreenEnd
                                )
                            }
                        }
                    Text(
                        modifier = Modifier
                            .width(95.dp)
                            .padding(end = 40.dp)
                            .align(Alignment.CenterEnd),
                        text = "$${price}",
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
                    .size(40.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    isClicked.value = true
                    selectedItemIndex.value = index
                    cartList.forEach{
                        if(it.title == items.title){
                            selectedItemIndex.value = cartList.indexOf(it)
                        }
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.dustbin),
                    contentDescription = "dust",
                    tint = GreenStart
                )
            }
        }
    }


    private fun incQuantity(rootDb: DatabaseReference, title: String, context: Context){
        rootDb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("cartItems").child(title).exists()){
                    val existsCount = dataSnapshot.child("cartItems").child(title).child("quantity").getValue(Int::class.java)
                    val quantity = existsCount!!+1
                    val exists = FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.user).child("cartItems").child(title)
                    exists.child("quantity").setValue(quantity)
                    Toast.makeText(context,"Item added", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors (e.g., log or display an error message)
            }
        })
    }


    private fun decQuantity(rootDb: DatabaseReference, title: String, context: Context){
        rootDb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("cartItems").child(title).exists()){
                    val existsCount = dataSnapshot.child("cartItems").child(title).child("quantity").getValue(Int::class.java)
                    val quantity = existsCount!!-1
                    val exists =  FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.user).child("cartItems").child(title)
                    exists.child("quantity").setValue(quantity)
                    if(existsCount <= 1){
                        rootDb.child("cartItems").child(title).removeValue()
                        isProcessed.value = false
                        Toast.makeText(context,"Item removed", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors (e.g., log or display an error message)
            }
        })
    }




}

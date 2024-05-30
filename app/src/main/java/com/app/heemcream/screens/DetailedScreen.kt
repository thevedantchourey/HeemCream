package com.app.heemcream.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.R
import com.app.heemcream.SaveSharedPreference
import com.app.heemcream.SetBarColor
import com.app.heemcream.model.Cart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



@Immutable
data class DetailedScreen(
    val icon: Int,
    val title: String,
    val subtitle: String,
    val price: Double,
    val ingredients: List<String>
):Screen {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {

        val rootDb = FirebaseDatabase.getInstance().getReference("Users")
        rootDb.keepSynced(true)

        val horizontalGradientBrush = Brush.verticalGradient(
            colors = listOf(
                Color(0xfff5f7fa),
                Color(0xffc3cfe2)
            )
        )

        val navigator =  LocalNavigator.current
        val context = LocalContext.current
        val user  = SaveSharedPreference.getUserName(context)

        var itemAdded by rememberSaveable{
            mutableIntStateOf(0)
        }


        SetBarColor(color = Color(0xfff5f7fa))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(horizontalGradientBrush)
                    .height(350.dp)
                    .fillMaxWidth()
                    .clickable { }
                    .padding(20.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Start),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color.White),
                    onClick = { navigator?.popUntilRoot() }
                ) {
                    Icon(
                        modifier = Modifier.size(60.dp).align(Alignment.CenterHorizontally).padding(vertical = 15.dp),
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = "dust",
                        tint = Color.DarkGray
                    )
                }
                Image(
                    modifier = Modifier
                        .width(180.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally),
                    alignment = Alignment.Center,
                    painter = painterResource(id = icon),
                    contentDescription = "discount.name"
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 290.dp, start = 20.dp, end = 20.dp),
                text = title,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                style = TextStyle(lineHeight = 20.sp),
                maxLines = 2,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 360.dp, start = 20.dp, end = 20.dp),
                text = subtitle,
                color = Color(0xffc3cfe2),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                style = TextStyle(lineHeight = 8.sp),
                maxLines = 2,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 420.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .width(140.dp),
                    text = "Ingredients",
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 8.sp),
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    ingredients.forEach{
                        SuggestionChip(modifier = Modifier,
                            onClick = { /*TODO*/ },
                            label = {
                                Text(
                                    modifier = Modifier,
                                    text = it,
                                    fontFamily = FontFamily(Font(R.font.rubik_regular)),
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .background(Color(0xffE6E5E5))
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
                    .height(82.dp)
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(Color(0xffE6E5E5))
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp),
                    text = "$${price}",
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(lineHeight = 8.sp),
                    maxLines = 2
                )
                Button(onClick =
                {
                    rootDb.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            var quantity = 1
                            val cartList =
                                Cart(
                                    icon = icon,
                                    title = title,
                                    subtitle = subtitle,
                                    quantity= quantity,
                                    price = price,
                                    ingredients = ingredients
                                )
                            if (!dataSnapshot.child(user).child("cartItems").child(title).exists()) {
                                rootDb.child(user).child("cartItems").child(cartList.title!!).setValue(cartList).addOnCompleteListener{
                                    Toast.makeText(context,"Added to cart", Toast.LENGTH_LONG).show()
                                    itemAdded = 1
                                }.addOnFailureListener{
                                    Toast.makeText(context,"Failed to add to cart", Toast.LENGTH_LONG).show()
                                }
                            } else {
                                if(dataSnapshot.child(user).child("cartItems").child(title).exists()){
                                    val existsCount = dataSnapshot.child(user).child("cartItems").child(title).child("quantity").getValue(Int::class.java)
                                    quantity = existsCount!!+1
                                    val exists = FirebaseDatabase.getInstance().getReference("Users").child(user).child("cartItems").child(cartList.title!!)
                                    exists.child("quantity").setValue(quantity)
                                }
                                Toast.makeText(context,"Item already exists in cart", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                        // Handle errors (e.g., log or display an error message)
                        }
                    })
                },
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffc3cfe8))
                ) {
                    var state by remember{
                        mutableStateOf("Add")
                    }
                    state = if(itemAdded == 1){ "Added" } else { "Add" }
                    
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = TextStyle(lineHeight = 8.sp),
                        )
                }
                itemAdded = 0
            }
        }
    }
}


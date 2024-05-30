package com.app.heemcream.screens


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.MainActivity
import com.app.heemcream.R
import com.app.heemcream.SaveSharedPreference
import com.app.heemcream.SetBarColor
import com.app.heemcream.data.AuthData
import com.app.heemcream.model.Cart
import com.app.heemcream.model.CartViewModel
import com.app.heemcream.model.DataState
import com.app.heemcream.ui.theme.RedStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowStart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class InfoScreen: Screen {


    @Composable
    override fun Content() {
        SetBarColor(Color.White)
        val viewModel: CartViewModel = viewModel()
        val rootDb = FirebaseDatabase.getInstance().getReference("Users")
        rootDb.keepSynced(true)


        val context = LocalContext.current
        val navigator =  LocalNavigator.current
        var userFocused by remember { mutableStateOf(false) }
        var passFocused by remember { mutableStateOf(false) }
        var locationFocused by remember { mutableStateOf(false) }
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var location by remember { mutableStateOf("") }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Card(
                    modifier = Modifier
                        .height(90.dp)
                        .width(200.dp)
                        .padding(top = 20.dp, end = 148.dp, bottom = 20.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color(0xfff5f7fa)),
                    onClick = { navigator?.pop() }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 15.dp),
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = "dust",
                        tint = Color.DarkGray
                    )
                }
                Text(
                    text = "Edit Info",
                    fontSize = 32.sp,
                    color = YellowStart,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.true_dream)),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 20.dp, end = 20.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .background(Color(0xffE6E5E5))
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Card(
                    modifier = Modifier
                        .height(130.dp)
                        .width(90.dp)
                        .padding(top = 40.dp),
                    shape = RoundedCornerShape(80.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .size(90.dp),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Inside,
                        painter = painterResource(id = R.drawable.no_profile),
                        contentDescription = ""
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(430.dp)
                        .padding(top = 20.dp)
                        .padding(20.dp),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(YellowEnd)
                            .fillMaxSize()
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp))
                    ){
                        OutlinedTextField(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(vertical = 30.dp)
                                .onFocusChanged { userFocused = it.isFocused },
                            value = username,
                            singleLine = true,
                            onValueChange = { username = it },
                            label = { Text("Username",color = if(userFocused){ YellowStart
                            }else{ Color.DarkGray}) },
                            colors =  OutlinedTextFieldDefaults.colors(focusedBorderColor = YellowStart, focusedTextColor = Color.DarkGray)
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(vertical = 110.dp)
                                .onFocusChanged { passFocused = it.isFocused },
                            value = password,
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            onValueChange = { password = it },
                            label = { Text("Password",color = if(passFocused){ YellowStart
                            }else{ Color.DarkGray}) },
                            colors =  OutlinedTextFieldDefaults.colors(focusedBorderColor = YellowStart, focusedTextColor = Color.DarkGray)
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 190.dp)
                                .onFocusChanged { locationFocused = it.isFocused },
                            value = location,
                            singleLine = true,
                            onValueChange = { location = it },
                            label = { Text("Location",color = if(locationFocused){ YellowStart
                            }else{ Color.DarkGray}) },
                            colors =  OutlinedTextFieldDefaults.colors(focusedBorderColor = YellowStart, focusedTextColor = Color.DarkGray)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .height(82.dp)
                                .background(Color.White)
                                .fillMaxWidth()
                                .padding(top = 2.dp)
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .background(Color.DarkGray)
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
                                    imageVector = Icons.Rounded.Favorite,
                                    contentDescription = "dust",
                                    tint = RedStart
                                )
                            }
                            Button(onClick = {
                                if(username.isEmpty() || password.isEmpty() || username == "" || password == ""){
                                    Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_LONG).show()
                                    return@Button
                                }
                                else {
                                    try{
                                        rootDb.addListenerForSingleValueEvent(object :
                                            ValueEventListener {
                                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                val user =
                                                    AuthData(
                                                        user = username,
                                                        pass = password,
                                                        location = location
                                                    )
                                                if (dataSnapshot.child(MainActivity.user).exists()) {
                                                    rootDb.child(user.user!!).setValue(user).addOnCompleteListener{
                                                        val oldUser = FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.user).child("user")
                                                        oldUser.removeValue().addOnCompleteListener{
                                                            setData(rootDb,viewModel,user)
                                                            val oldData = FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.user)
                                                            oldData.removeValue()
                                                        }
                                                        SaveSharedPreference.setUserName(context,username)
                                                        SaveSharedPreference.setLocation(context,location)
                                                        username = ""
                                                        password = ""
                                                        location = ""
                                                        Toast.makeText(context,"Information Updated", Toast.LENGTH_LONG).show()
                                                    }.addOnFailureListener{
                                                        Toast.makeText(context,"Failed to update info", Toast.LENGTH_LONG).show()
                                                    }
                                                    val intent = Intent(context, MainActivity::class.java)
                                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                    context.startActivity(intent)
                                                }
                                            }

                                            override fun onCancelled(databaseError: DatabaseError) {}
                                        })
                                    }catch (e:Exception){
                                        Toast.makeText(context,"check if your username contains : '.', '#', '$', '[', ']' ", Toast.LENGTH_LONG).show()
                                    }
                                }
                            },
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(50.dp)
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xffc3cfe8))
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Update",
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
        }
    }

    fun setData(rootDb:DatabaseReference,viewModel: CartViewModel,user:AuthData) {
        when(val cart = viewModel.response.value){
            is DataState.Loading ->{}
            is DataState.Success -> {
                itemData(rootDb, cartList = cart.data, user = user.user!!)
            }
            is DataState.Failure -> {}
            is DataState.Empty -> {}
        }
    }

    private fun itemData(rootDb:DatabaseReference, cartList: MutableList<Cart>?, user:String){
        cartList!!.forEachIndexed{index ,_ ->
            rootDb.child(user).child("cartItems").child(cartList[index].title!!).setValue(cartList[index])
            rootDb.keepSynced(true)
        }
    }
}

package com.app.heemcream.components


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.heemcream.R
import com.app.heemcream.data.AuthData
import com.app.heemcream.screens.LoginScreen
import com.app.heemcream.ui.theme.RedStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowStart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Preview
@Composable
fun SignUpCenter(){
    var userFocused by remember { mutableStateOf(false) }
    var passFocused by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val navigator =  LocalNavigator.current

    val rootDb = FirebaseDatabase.getInstance().getReference("Users")
    rootDb.keepSynced(true)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(360.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 18.dp)
            .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp))
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
                    .padding(vertical = 40.dp)
                    .onFocusChanged { userFocused = it.isFocused },
                value = username,
                onValueChange = { username = it },
                label = { Text("Username",color = if(userFocused){ YellowStart}else{ Color.DarkGray}) },
                colors =  OutlinedTextFieldDefaults.colors(focusedBorderColor = YellowStart, focusedTextColor = Color.DarkGray)
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 120.dp)
                    .onFocusChanged { passFocused = it.isFocused },
                visualTransformation = PasswordVisualTransformation(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password",color = if(passFocused){ YellowStart}else{ Color.DarkGray}) },
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
                        rootDb.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                try {
                                    val user =
                                        AuthData(
                                            user = username,
                                            pass = password
                                        )
                                    if (!dataSnapshot.child(username).exists()) {
                                        rootDb.child(username).setValue(user).addOnCompleteListener{
                                            navigator?.push(LoginScreen())
                                            Toast.makeText(context,"User created successfully", Toast.LENGTH_LONG).show()
                                        }.addOnFailureListener{
                                            Toast.makeText(context,"Failed to create a new user", Toast.LENGTH_LONG).show()
                                        }
                                    } else {
                                        Toast.makeText(context,"User already exists with this username ", Toast.LENGTH_LONG).show()
                                    }

                                }catch (e:Exception){
                                    Toast.makeText(context,"check if your username contains : '.', '#', '$', '[', ']' ", Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                Toast.makeText(context,"check if your username contains : '.', '#', '$', '[', ']' ", Toast.LENGTH_LONG).show()
                            }
                        })

                    }
                },
                    modifier = Modifier
                        .width(160.dp)
                        .height(50.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffc3cfe8))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Sign Up",
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
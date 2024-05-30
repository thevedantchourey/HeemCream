package com.app.heemcream.components

import android.content.Intent
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
import com.app.heemcream.MainActivity
import com.app.heemcream.R
import com.app.heemcream.SaveSharedPreference
import com.app.heemcream.ui.theme.RedStart
import com.app.heemcream.ui.theme.YellowEnd
import com.app.heemcream.ui.theme.YellowStart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



@Preview
@Composable
fun LoginCenter(){
    var userFocused by remember { mutableStateOf(false) }
    var passFocused by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

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
                        try{
                            rootDb.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    if (dataSnapshot.child(username).exists()) {
                                        val user =  dataSnapshot.child(username).child("user").getValue(String::class.java)
                                        val pass =  dataSnapshot.child(username).child("pass").getValue(String::class.java)
                                        if(username == user && password == pass){
                                            SaveSharedPreference.setUserName(context, username)
                                            val intent  = Intent(context, MainActivity::class.java)
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            context.startActivity(intent)
                                            Toast.makeText(context,"Logged In", Toast.LENGTH_LONG).show()
                                        }else{
                                            Toast.makeText(context,"User does not exist please Sign-Up first", Toast.LENGTH_LONG).show()
                                        }
                                    }else{
                                        Toast.makeText(context,"User does not exist please Sign-Up first", Toast.LENGTH_LONG).show()
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle errors (e.g., log or display an error message)
                                }
                            })
                        }catch (e:Exception){
                            Toast.makeText(context,"check if your username contains : '.', '#', '$', '[', ']' ", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffc3cfe8))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Login",
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
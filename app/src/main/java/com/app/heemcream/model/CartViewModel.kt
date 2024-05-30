package com.app.heemcream.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.app.heemcream.MainActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class CartViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init{
        getResponse()
    }

    private fun getResponse(){

        val tempList = mutableStateListOf<Cart>()
        val upList =  mutableStateListOf<Cart>()


        response.value = DataState.Loading
        FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.user).child("cartItems")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    tempList.clear()
                    if (snapshot.exists()){
                        snapshot.ref.keepSynced(true)
                        for(snap in snapshot.children){
                            val data = snap.getValue(Cart::class.java)
                            if (data != null){
                                tempList.add(data)
                                upList.swapList(tempList)
                            }
                        }
                        response.value = DataState.Success(upList)
                    }else{
                        response.value = DataState.Empty
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failure
                }

            })
    }


    fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
        clear()
        addAll(newList)
    }

}
package com.imtrying.bank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class DisplayAllCustomers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_all_customers)
        val context = this
        val db = DataBaseHandler(context)

        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //adding a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

       // db.deleteTable()

        //adding some dummy data to the list
/*val user1 = User("Shru Khan", "srk@what.com", 3000)
val user2 = User("Leo Messi", "goat@bdor.org", 988837, 10)
val user3 = User("Faiz Khan", "Faiz@blahblah.com", 777652, 7)
val user4 = User("Cristiano Ronaldo", "crhis69@123.com", 12233498)
val user5 = User("Junaid Nooh", "zackthunder@gmail.com", 999999)

val userX = arrayListOf<User>(user1, user2, user3, user4, user5)

for(i in 0 until userX.size)
{ db.insertData(userX[i]) }*/

//crating an array list to store users using the data class user
val users = db.readData()

//creating our adapter
val adapter = CustomAdapter(users, { user: User -> itemClicked(user) })

//now adding the adapter to recyclerview
recyclerView.adapter = adapter

//db.deleteTable()
}

    private fun itemClicked(user: User) {
        //Toast.makeText(this, "Clicked: ${user.name}", Toast.LENGTH_LONG).show()
        val intent = Intent(this, SeeDeets::class.java)
        intent.putExtra("ID", user.id)
        startActivity(intent)
    }
}
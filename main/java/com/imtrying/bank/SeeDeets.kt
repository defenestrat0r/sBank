package com.imtrying.bank

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_see_deets.*
import org.w3c.dom.Text

class SeeDeets : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_deets)

        val userId= intent.getIntExtra("ID", 2)

        val context = this
        val db = DataBaseHandler(context)

        val foundUser = db.findUser(userId)
        val uName = findViewById<TextView>(R.id.nameTextView)
        val uMail = findViewById<TextView>(R.id.emailTextView)
        val uID = findViewById<TextView>(R.id.idTextView)
        val uBalance = findViewById<TextView>(R.id.balanceTextView)

        uName.text = foundUser.name
        uMail.text = foundUser.email
        uID.text = foundUser.id.toString()
        uBalance.text = foundUser.bal.toString()

        val search = findViewById<SearchView>(R.id.searchView)
        
        search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                //Toast.makeText(applicationContext, newText, Toast.LENGTH_SHORT).show()
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                //Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                blammo(query, uName.text.toString())
                return false
            }
        })
    }

    private fun blammo(query: String, main: String) {
        val intent = Intent(this, Transfer::class.java)

        val context = this
        val db = DataBaseHandler(context)

        val queryID = db.findUserName(query)
        //Toast.makeText(applicationContext, queryID, Toast.LENGTH_SHORT).show()
        //Toast.makeText(applicationContext, main, Toast.LENGTH_SHORT).show()
        val users = arrayOf(main, queryID)
        intent.putExtra("names", users)
        startActivity(intent)
    }
}



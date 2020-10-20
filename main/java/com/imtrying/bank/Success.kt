package com.imtrying.bank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Success : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, DisplayAllCustomers::class.java)
            startActivity(mainIntent)
            finish()
        }, 2000)
    }
}
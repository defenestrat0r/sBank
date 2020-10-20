package com.imtrying.bank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_card1.*
import java.util.*

class card1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card1)

        //val img: ImageView = findViewById<ImageView>(R.id.thumbnail)
        /*val drawables = arrayOf(R.drawable.photo1, R.drawable.photo2, R.drawable.photo3)
        val rand = Random()
        val rndInt: Int = rand.nextInt(drawables.size) + 1
        val drawableName = "photo$rndInt"
        //val resID = resources.getIdentifier(drawableName, "drawable", packageName)
        img.setImageResource(drawables[rndInt])*/


    }
}
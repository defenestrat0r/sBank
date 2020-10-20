package com.imtrying.bank

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_card1.*
import kotlinx.android.synthetic.main.activity_card1.view.*

class CustomAdapter(private val userList: ArrayList<User>, val clickListener: (User) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_card1, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position], clickListener)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(user: User, clickListener: (User) -> Unit) {
            val textViewName = itemView.findViewById<TextView>(R.id.textViewUsername)
            val textViewEmail  = itemView.findViewById<TextView>(R.id.textViewEmail)
            val textViewID = itemView.findViewById<TextView>(R.id.textViewID)
            textViewName.text = user.name
            textViewEmail.text = user.email
            textViewID.text = user.id.toString()
            itemView.setOnClickListener { clickListener(user) }
        }
    }
}

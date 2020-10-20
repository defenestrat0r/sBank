package com.imtrying.bank

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_all_customers.*

class AllCustomers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_customers)
        val context = this
        val db = DataBaseHandler(context)
        btnInsert.setOnClickListener {
            if (editTextName.text.toString().isNotEmpty() &&
                editTextEmail.text.toString().isNotEmpty() &&
                    editTextBalance.text.toString().isNotEmpty() ) {
                val user = User(editTextName.text.toString(), editTextEmail.text.toString(), editTextBalance.text.toString().toInt())
                db.insertData(user)
                clearField()
            }
            else {
                Toast.makeText(context, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        }
        btnRead.setOnClickListener {
            val data = db.readData()
            tvResult.text = ""
            for (i in 0 until data.size) {
                tvResult.append(
                    data[i].id.toString() + " " + data[i].name + " " + data[i].email + " " + data[i].bal.toString() +"\n"
                )
            }
        }
    }
    private fun clearField() {
        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextBalance.text.clear()
    }
}

package com.imtrying.bank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_transfer.*
import org.w3c.dom.Text

class Transfer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        val context = this
        val db = DataBaseHandler(context)

        val userNames= intent.getStringArrayExtra("names")
        val sender = userNames?.get(0)
        val receive = userNames?.get(1)

        val send = findViewById<TextView>(R.id.senderTextView)
        val rec = findViewById<TextView>(R.id.recieverTextView)
        val but1 = findViewById<Button>(R.id.btnClear)
        val but2 = findViewById<Button>(R.id.btnSend)
        val num = findViewById<EditText>(R.id.editTextNumber)
        val bal1 = findViewById<TextView>(R.id.senderBalance)
        val bal2 = findViewById<TextView>(R.id.recieverBalance)


        send.text = sender
        rec.text = receive

        val sendUser = db.findUser(send.text.toString())
        val recUser = db.findUser(rec.text.toString())

        bal1.text = sendUser.bal.toString()
        bal2.text = recUser.bal.toString()

        /*num.addTextChangedListener {
            fun afterTextChanged(s: Editable) {
            }

            fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (num.text == null) { num.text = Editable.Factory.getInstance().newEditable("0") }
            }
        }*/

        but1.setOnClickListener {
            num.text = Editable.Factory.getInstance().newEditable("0")
        }
        but2.setOnClickListener {
            val transfer = Integer.parseInt(num.text.toString())

            if(transfer <= 0) {
                Toast.makeText(applicationContext, "Zero sum fund transfer is not allowed!", Toast.LENGTH_LONG).show()
            }
            else if (sendUser.bal < transfer){
                Toast.makeText(applicationContext, "Insufficient Funds!", Toast.LENGTH_LONG).show()
            }
            else {
                //Toast.makeText(applicationContext, recUser.name, Toast.LENGTH_LONG).show()
                db.fundTransfer(sendUser, recUser, transfer)

                val intent = Intent(this, Success::class.java)
                startActivity(intent)
            }
        }
    }
}
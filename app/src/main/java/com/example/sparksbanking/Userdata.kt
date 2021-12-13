package com.example.sparksbanking

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.github.muddz.styleabletoast.StyleableToast

class Userdata : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var email:TextView
    private lateinit var accountNo:TextView
    private lateinit var balance:TextView
    private lateinit var transferMoney: Button
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdata)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email_id)
        accountNo = findViewById(R.id.account_no)
        balance = findViewById(R.id.avail_balance)
        transferMoney = findViewById(R.id.transfer_money)


        // Getting the intent
        val intent = intent
        val extras = intent.extras

        // Extracting the data

        // Extracting the data
        if (extras != null) {
            name.text = extras.getString("NAME")
            accountNo.text = extras.getInt("ACCOUNT_NO").toString()
            email.text = extras.getString("EMAIL")
            balance.text = extras.getString("BALANCE")
        } else {
            Log.d("TAG", "Empty Intent")
        }

        transferMoney.setOnClickListener{
            enteramount()
        }
    }

    private fun enteramount() {
        val mBuilder = AlertDialog.Builder(this@Userdata)
        val mView = layoutInflater.inflate(R.layout.dialog_box, null)
        mBuilder.setTitle("Enter Amount").setView(mView).setCancelable(false)

        val mAmount = mView.findViewById<View>(R.id.enter_money) as EditText
        mBuilder.setPositiveButton("SEND"
        ) { dialogInterface, i -> }.setNegativeButton("CANCEL"
        ) { dialog, which ->
            dialog.dismiss()
            transactionCancel()
        }

        dialog = mBuilder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // Checking whether amount entered is correct or not
            val currentBalance = balance.text.toString().toInt()
            if (mAmount.text.toString().isEmpty()) {
                mAmount.error = "Amount can't be empty"
            } else if (mAmount.text.toString().toInt() > currentBalance) {
                mAmount.error = "Your account don't have enough balance"
            } else {
                val intent = Intent(this@Userdata, senduser::class.java)
                intent.putExtra("FROM_USER_ACCOUNT_NO",
                    accountNo.text.toString().toInt()) // PRIMARY_KEY
                intent.putExtra("FROM_USER_NAME", name.text)
                intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.text)
                intent.putExtra("TRANSFER_AMOUNT", mAmount.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }

    private fun transactionCancel() {
        val builder_exitbutton = AlertDialog.Builder(this@Userdata)
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
            .setPositiveButton("yes"
            ) { dialogInterface, i ->
                StyleableToast.makeText(this, "Transaction Cancelled", Toast.LENGTH_LONG,R.style.exampleToast1).show();
            }.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
                enteramount()
            }
        val alertexit = builder_exitbutton.create()
        alertexit.show()
    }
}
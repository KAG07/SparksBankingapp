package com.example.sparksbanking

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbanking.Adapter.senduseradapter
import com.example.sparksbanking.DB.Transactioncontract
import com.example.sparksbanking.DB.Transactiondb
import com.example.sparksbanking.DB.Usercontract
import com.example.sparksbanking.DB.Userdb
import com.example.sparksbanking.Model.User
import io.github.muddz.styleabletoast.StyleableToast
import java.text.SimpleDateFormat
import java.util.*


class senduser : AppCompatActivity(), senduseradapter.OnUserListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var userArrayList: ArrayList<User>

    private lateinit var dbHelper: Userdb

    private lateinit var date: String
    private lateinit var time:String
    var fromUserAccountNo = 0
    var toUserAccountNo:Int = 0
    var toUserAccountBalance:Int = 0
    private lateinit var fromUserAccountName: String
    private lateinit var fromUserAccountBalance:String
    private lateinit var transferAmount:String
    private lateinit var toUserAccountName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_senduser)


        // Get time instance
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy, hh:mm a")
        val date_and_time = simpleDateFormat.format(calendar.time)

        // Get Intent

        // Get Intent
        val bundle = intent.extras
        if (bundle != null) {
            fromUserAccountName = bundle.getString("FROM_USER_NAME").toString()
            fromUserAccountNo = bundle.getInt("FROM_USER_ACCOUNT_NO")
            fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE").toString()
            transferAmount = bundle.getString("TRANSFER_AMOUNT")!!
        }

        // Create ArrayList of Users

        // Create ArrayList of Users
        userArrayList = ArrayList()

        // Create Table in the Database

        // Create Table in the Database
        dbHelper = Userdb(this)

        // Show list of items

        // Show list of items
        recyclerView = findViewById(R.id.send_to_user_list)
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        myAdapter = senduseradapter(userArrayList, this)
        recyclerView.adapter = myAdapter
    }

    override fun onUserClick(position: Int) {

        // Insert data into transactions table
        toUserAccountNo = userArrayList[position].getacount()
        toUserAccountName = userArrayList[position].getName()
        toUserAccountBalance = userArrayList[position].getbalance()

        calculateAmount()

        Transactiondb(this).insertTransferData(fromUserAccountName,
            toUserAccountName,
            transferAmount,
            1)
        StyleableToast.makeText(this, "Transaction Success", Toast.LENGTH_LONG,R.style.exampleToast).show();

        startActivity(Intent(this@senduser, MainScreen::class.java))
        finish()
    }
    override fun onStart() {
        super.onStart()
        displayDatabaseInfo()
    }

    private fun calculateAmount() {
        val currentAmount = fromUserAccountBalance.toInt()
        val transferAmountInt = transferAmount.toInt()
        val remainingAmount = currentAmount - transferAmountInt
        val increasedAmount = transferAmountInt + toUserAccountBalance

        // Update amount in the dataBase
        Userdb(this).updateAmount(fromUserAccountNo, remainingAmount)
        Userdb(this).updateAmount(toUserAccountNo, increasedAmount)
    }

    override fun onBackPressed() {
        val builder_exitButton = AlertDialog.Builder(this@senduser)
        builder_exitButton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
            .setPositiveButton("yes") { dialogInterface, i -> // Transactions Cancelled
                val dbHelper = Transactiondb(applicationContext)
                val db: SQLiteDatabase = dbHelper.getWritableDatabase()
                val values = ContentValues()
                values.put(Transactioncontract.COLUMN_FROM_NAME, fromUserAccountName)
                values.put(Transactioncontract.COLUMN_TO_NAME, toUserAccountName)
                values.put(Transactioncontract.COLUMN_STATUS, 0)
                values.put(Transactioncontract.COLUMN_AMOUNT, transferAmount)
                db.insert(Transactioncontract.TABLE_NAME, null, values)
                StyleableToast.makeText(this, "Transaction Cancelled", Toast.LENGTH_LONG,R.style.exampleToast1).show();
                startActivity(Intent(this@senduser, UserList::class.java))
                finish()
            }.setNegativeButton("No", null)
        val alertExit = builder_exitButton.create()
        alertExit.show()
    }

    private fun displayDatabaseInfo() {
        // Create and/or open a database to read from it
        val db = dbHelper.readableDatabase
        val projection = arrayOf<String>(
            Usercontract.COLUMN_USER_NAME,
            Usercontract.COLUMN_USER_ACCOUNT_BALANCE,
            Usercontract.COLUMN_USER_ACCOUNT_NUMBER,
            Usercontract.COLUMN_USER_EMAIL)
        val cursor = db.query(
            Usercontract.TABLE_NAME,  // The table to query
            projection,  // The columns to return
            null,  // The columns for the WHERE clause
            null,  // The values for the WHERE clause
            null,  // Don't group the rows
            null,  // Don't filter by row groups
            null) // The sort order
        try {
            // Figure out the index of each column
            val emailColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_EMAIL)
            val accountNumberColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_ACCOUNT_NUMBER)
            val nameColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_NAME)
            val accountBalanceColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_ACCOUNT_BALANCE)

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                val currentName = cursor.getString(nameColumnIndex)
                val accountNumber = cursor.getInt(accountNumberColumnIndex)
                val email = cursor.getString(emailColumnIndex)
                val accountBalance = cursor.getInt(accountBalanceColumnIndex)

                // Display the values from each column of the current row in the cursor in the TextView
                userArrayList.add(User(currentName,
                    accountNumber,
                    accountBalance,
                    email))
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close()
        }
    }
}
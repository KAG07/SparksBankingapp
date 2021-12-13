package com.example.sparksbanking

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbanking.Adapter.Transactionadapter
import com.example.sparksbanking.DB.Transactioncontract
import com.example.sparksbanking.DB.Transactiondb
import com.example.sparksbanking.Model.Transaction
import com.example.sparksbanking.Model.User
import java.util.ArrayList

class Transactions : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var transactionArrayList: ArrayList<Transaction>

    private lateinit var dbHelper: Transactiondb

    lateinit var emptyList: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        // Get TextView
        emptyList = findViewById(R.id.empty_text)

        // Create Transaction History List

        // Create Transaction History List
        transactionArrayList = ArrayList()

        // Create Table in the Database

        // Create Table in the Database
        dbHelper = Transactiondb(this)

        // Display database info

        // Display database info
        displayDatabaseInfo()

        recyclerView = findViewById(R.id.transactions_list)
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        myAdapter = Transactionadapter(this, transactionArrayList)
        recyclerView.adapter = myAdapter
    }

    private fun displayDatabaseInfo() {
        Log.d("TAG", "displayDataBaseInfo()")

        // Create and/or open a database to read from it
        val db = dbHelper.readableDatabase
        Log.d("TAG", "displayDataBaseInfo()1")
        val projection = arrayOf<String>(
            Transactioncontract.COLUMN_FROM_NAME,
            Transactioncontract.COLUMN_TO_NAME,
            Transactioncontract.COLUMN_AMOUNT,
            Transactioncontract.COLUMN_STATUS
        )
        Log.d("TAG", "displayDataBaseInfo()2")
        val cursor = db.query(
            Transactioncontract.TABLE_NAME,  // The table to query
            projection,  // The columns to return
            null,  // The columns for the WHERE clause
            null,  // The values for the WHERE clause
            null,  // Don't group the rows
            null,  // Don't filter by row groups
            null) // The sort order
        try {
            // Figure out the index of each column
            val fromNameColumnIndex =
                cursor.getColumnIndex(Transactioncontract.COLUMN_FROM_NAME)
            val ToNameColumnIndex =
                cursor.getColumnIndex(Transactioncontract.COLUMN_TO_NAME)
            val amountColumnIndex =
                cursor.getColumnIndex(Transactioncontract.COLUMN_AMOUNT)
            val statusColumnIndex =
                cursor.getColumnIndex(Transactioncontract.COLUMN_STATUS)
            Log.d("TAG", "displayDataBaseInfo()3")

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                val fromName = cursor.getString(fromNameColumnIndex)
                val ToName = cursor.getString(ToNameColumnIndex)
                val accountBalance = cursor.getInt(amountColumnIndex)
                val status = cursor.getInt(statusColumnIndex)


                //Log.d("TAG", "displayDataBaseInfo()4");

                // Display the values from each column of the current row in the cursor in the TextView
                transactionArrayList.add(Transaction(fromName, ToName, accountBalance, status))
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close()
        }
        if (transactionArrayList.isEmpty()) {
            emptyList.visibility = View.VISIBLE
        } else {
            emptyList.visibility = View.GONE
        }
    }
}
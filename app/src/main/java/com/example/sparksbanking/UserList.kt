package com.example.sparksbanking

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.sparksbanking.Adapter.userlistadapter
import com.example.sparksbanking.DB.Usercontract
import com.example.sparksbanking.DB.Userdb
import com.example.sparksbanking.Model.User
import java.util.ArrayList

class UserList : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var userArrayList: ArrayList<User>

    private lateinit var dbHelper: Userdb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userArrayList = ArrayList<User>()

        // Create Table in the Database
        dbHelper = Userdb(this)

        // Read Data from DataBase

        // Read Data from DataBase
        displayDatabaseInfo()

        // Show list of items

        // Show list of items
        recyclerView = findViewById(R.id.all_users_list)
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        myAdapter = userlistadapter(this, userArrayList)
        recyclerView.adapter = myAdapter
    }

    private fun displayDatabaseInfo() {
        userArrayList.clear()

        val cursor: Cursor = Userdb(this).readAllData()


        val emailColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_EMAIL)
        val accountNumberColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_ACCOUNT_NUMBER)
        val nameColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_NAME)
        val accountBalanceColumnIndex = cursor.getColumnIndex(Usercontract.COLUMN_USER_ACCOUNT_BALANCE)

        while (cursor.moveToNext()) {
            val currentName = cursor.getString(nameColumnIndex)
            val accountNumber = cursor.getInt(accountNumberColumnIndex)
            val email = cursor.getString(emailColumnIndex)
            val accountBalance = cursor.getInt(accountBalanceColumnIndex)

            // Display the values from each column of the current row in the cursor in the TextView

            // Display the values from each column of the current row in the cursor in the TextView
            userArrayList.add(User(currentName,
                accountNumber,
                accountBalance,
                email))
        }
    }
}
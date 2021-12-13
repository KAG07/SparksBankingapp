package com.example.sparksbanking.DB

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sparksbanking.DB.Usercontract.Companion.TABLE_NAME

class Userdb(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
    private var DATABASE_NAME = "User.db"
    private val DATABASE_VERSION = 1
}

    override fun onCreate(db: SQLiteDatabase?) {

        // Create a String that contains the SQL statement to create the pets table
        val SQL_CREATE_USER_TABLE = ("CREATE TABLE " + Usercontract.TABLE_NAME.toString() + " ("
                + Usercontract.COLUMN_USER_ACCOUNT_NUMBER.toString() + " INTEGER, "
                + Usercontract.COLUMN_USER_NAME.toString() + " VARCHAR, "
                + Usercontract.COLUMN_USER_EMAIL.toString() + " VARCHAR, "
                + Usercontract.COLUMN_USER_ACCOUNT_BALANCE.toString() + " INTEGER NOT NULL);")

        // Execute the SQL statement
        db!!.execSQL(SQL_CREATE_USER_TABLE)


        // Insert Into Table
        db.execSQL("insert into $TABLE_NAME values(7860,'CUSTOMER1', 'CUSTOMER1@gmail.com', 150000)")
        db.execSQL("insert into $TABLE_NAME values(5862,'CUSTOMER2', 'CUSTOMER2@gmail.com', 50000)")
        db.execSQL("insert into $TABLE_NAME values(7895,'CUSTOMER3', 'CUSTOMER3@gmail.com', 100000)")
        db.execSQL("insert into $TABLE_NAME values(1258,'CUSTOMER4', 'CUSTOMER4@gmail.com', 80000)")
        db.execSQL("insert into $TABLE_NAME values(7410,'CUSTOMER5', 'CUSTOMER5@gmail.com', 70500)")
        db.execSQL("insert into $TABLE_NAME values(8529,'CUSTOMER6', 'CUSTOMER6@gmail.com', 65000)")
        db.execSQL("insert into $TABLE_NAME values(3698,'CUSTOMER7', 'CUSTOMER7@gmail.com', 145000)")
        db.execSQL("insert into $TABLE_NAME values(7853,'CUSTOMER8', 'CUSTOMER8@gmail.com', 125000)")
        db.execSQL("insert into $TABLE_NAME values(4562,'CUSTOMER9', 'CUSTOMER9@gmail.com', 105000)")
        db.execSQL("insert into $TABLE_NAME values(2365,'CUSTOMER10', 'CUSTOMER10@gmail.com', 99900)")

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        if (p1 != p2) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + Usercontract.TABLE_NAME)
            onCreate(db)
        }
    }

    fun readAllData(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from " + Usercontract.TABLE_NAME, null)
    }

    fun readParticularData(accountNo: Int): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from " + Usercontract.TABLE_NAME.toString() + " where " +
                Usercontract.COLUMN_USER_ACCOUNT_NUMBER.toString() + " = " + accountNo, null)
    }

    fun updateAmount(accountNo: Int, amount: Int) {
        Log.d("TAG", "update Amount")
        val db = this.writableDatabase
        db.execSQL("update " + Usercontract.TABLE_NAME.toString() + " set " + Usercontract.COLUMN_USER_ACCOUNT_BALANCE.toString() + " = " + amount.toString() + " where " +
                Usercontract.COLUMN_USER_ACCOUNT_NUMBER.toString() + " = " + accountNo)
    }


}
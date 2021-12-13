package com.example.sparksbanking.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Transactiondb(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
companion object{
    private const val DATABASE_NAME = "transaction.db"

    private const val DATABASE_VERSION = 1
}

    override fun onCreate(db: SQLiteDatabase) {

        // Create a String that contains the SQL statement to create the pets table
        val SQL_CREATE_TRANSACTION_TABLE =
            ("CREATE TABLE " + Transactioncontract.TABLE_NAME.toString() + " ("
                    + Transactioncontract.COLUMN_FROM_NAME.toString() + " VARCHAR, "
                    + Transactioncontract.COLUMN_TO_NAME.toString() + " VARCHAR, "
                    + Transactioncontract.COLUMN_AMOUNT.toString() + " INTEGER, "
                    + Transactioncontract.COLUMN_STATUS.toString() + " INTEGER);")

        // Execute the SQL statement

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        if (p1 != p2) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + Transactioncontract.TABLE_NAME)
            onCreate(db)
        }
    }

    fun insertTransferData(
        fromName: String,
        toName: String,
        amount: String,
        status: Int,
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Transactioncontract.COLUMN_FROM_NAME, fromName)
        contentValues.put(Transactioncontract.COLUMN_TO_NAME, toName)
        contentValues.put(Transactioncontract.COLUMN_AMOUNT, amount)
        contentValues.put(Transactioncontract.COLUMN_STATUS, status)
        val result = db.insert(Transactioncontract.TABLE_NAME, null, contentValues)
        return result != -1L
    }

}
package com.example.sparksbanking.DB

import android.provider.BaseColumns

class Transactioncontract():BaseColumns {
    companion object{
    val TABLE_NAME = "Transaction_table"

    /**Table Fields */
    val _ID = BaseColumns._ID
    val COLUMN_FROM_NAME = "from_name"
    val COLUMN_TO_NAME = "to_name"
    val COLUMN_AMOUNT = "amount"
    val COLUMN_STATUS = "status"}
}
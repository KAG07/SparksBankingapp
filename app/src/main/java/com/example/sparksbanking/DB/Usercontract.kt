package com.example.sparksbanking.DB

import android.provider.BaseColumns

class Usercontract() :BaseColumns{
companion object{
    val TABLE_NAME = "user"


    val _ID = BaseColumns._ID
    val COLUMN_USER_NAME = "name"
    val COLUMN_USER_ACCOUNT_NUMBER = "accountNo"
    val COLUMN_USER_EMAIL = "email"
    val COLUMN_USER_ACCOUNT_BALANCE = "balance"}
}
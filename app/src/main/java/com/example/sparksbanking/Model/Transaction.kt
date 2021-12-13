package com.example.sparksbanking.Model

class Transaction(fuser:String, tUser:String, amt:Int,  sttus:Int) {
    private var fromUser: String=fuser
    private var ToUser: String=tUser
    private var amountTransferred = amt
    private var status = sttus


    fun getFromUser(): String? {
        return fromUser
    }

    fun setFromUser(fromUser: String?) {
        this.fromUser = fromUser!!
    }

    fun getToUser(): String? {
        return ToUser
    }

    fun setToUser(toUser: String) {
        ToUser = toUser
    }

    fun getAmountTransferred(): Int {
        return amountTransferred
    }

    fun setAmountTransferred(amountTransferred: Int) {
        this.amountTransferred = amountTransferred
    }

    fun getStatus(): Int {
        return status
    }

    fun setStatus(status: Int) {
        this.status = status
    }
}
package com.example.sparksbanking.Model

class User(nam:String,acn:Int,bal:Int,mail:String) {
    private var name: String=nam
    private var accountNumber = acn
    private var balance = bal
    private var email: String=mail


    fun getName(): String {
        return name
    }
    fun getacount(): Int {
        return accountNumber
    }
    fun getbalance(): Int {
        return balance
    }
    fun getmail(): String {
        return email
    }

    fun setName(s:String){
         name=s
    }
    fun setacount(a:Int){
         accountNumber=a
    }
    fun setbalance(b:Int ){
        balance=b
    }
    fun setmail(mail:String){
        email=mail
    }


}
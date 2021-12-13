package com.example.sparksbanking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button


class MainScreen : AppCompatActivity() {
    private lateinit var btn1:Button
    private lateinit var btn2:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1=findViewById(R.id.showuser)
        btn2=findViewById(R.id.showtransaction)
        btn1.setOnClickListener {
            var intent=Intent(this,UserList::class.java)
            startActivity(intent)

        }
        btn2.setOnClickListener {
            var intent=Intent(this,Transactions::class.java)
            startActivity(intent)
        }
    }
}
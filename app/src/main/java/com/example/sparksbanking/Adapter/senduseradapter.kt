package com.example.sparksbanking.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbanking.Model.User
import com.example.sparksbanking.R
import java.lang.String
import java.util.ArrayList

class senduseradapter(var userArrayList:ArrayList<User>, var onUserListener:OnUserListener):RecyclerView.Adapter<senduseradapter.viewholder>() {


    inner class viewholder( itemview: View, var onUserListener:OnUserListener): RecyclerView.ViewHolder(itemview),View.OnClickListener {
        public var userName = itemview.findViewById<TextView>(R.id.username)
        public  var userAccountBalance = itemview.findViewById<TextView>(R.id.amount)
        init {
            itemview.setOnClickListener(this)
        }
        override fun onClick(p0: View) {
            onUserListener.onUserClick(adapterPosition)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.userlist_item, parent, false)
        return viewholder(view, onUserListener)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.itemView.setTag(userArrayList[position])
        holder.userName.setText(userArrayList[position].getName())
        holder.userAccountBalance.setText(String.format("%d", userArrayList[position].getbalance()))
    }

    override fun getItemCount(): Int {
       return userArrayList.size
    }
    interface OnUserListener {
        fun onUserClick(position: Int)
    }
}
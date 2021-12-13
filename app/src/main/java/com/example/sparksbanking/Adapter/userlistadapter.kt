package com.example.sparksbanking.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbanking.Model.User
import com.example.sparksbanking.R
import com.example.sparksbanking.Userdata
import java.lang.String
import java.util.ArrayList

class userlistadapter(context: Context, private var userArrayList:ArrayList<User>):RecyclerView.Adapter<userlistadapter.viewholder>() {


    inner class viewholder(@NonNull var itemview: View):RecyclerView.ViewHolder(itemview){
        var usrname:TextView = itemview.findViewById(R.id.username);
        var Balance:TextView = itemview.findViewById(R.id.amount);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view=LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist_item,parent,false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.itemview.tag = userArrayList[position]
        holder.usrname.text = userArrayList[position].getName()
        holder.Balance.text=userArrayList[position].getbalance().toString()

        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val intent = Intent(v.context, Userdata::class.java)
            intent.putExtra("ACCOUNT_NO", userArrayList[position].getacount())
            intent.putExtra("NAME", userArrayList[position].getName())
            intent.putExtra("EMAIL", userArrayList[position].getmail())
            intent.putExtra("BALANCE", String.valueOf(userArrayList[position].getbalance()))
            v.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
       return userArrayList.size
    }
}
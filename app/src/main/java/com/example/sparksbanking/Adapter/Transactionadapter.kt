package com.example.sparksbanking.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sparksbanking.Model.Transaction
import com.example.sparksbanking.R
import java.lang.String

class Transactionadapter(context:Context,private var list:ArrayList<Transaction>):RecyclerView.Adapter<Transactionadapter.viewholder>() {

    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fromName: TextView=itemView.findViewById(R.id.t_from_name)
        var toName: TextView=itemView.findViewById(R.id.t_to_name)
        var amountTransferred:TextView=itemView.findViewById(R.id.t_amount)
        private   var date:TextView=itemView.findViewById(R.id.t_date)
        private   var time:TextView=itemView.findViewById(R.id.t_time)
        var cardView: CardView=itemView.findViewById(R.id.transaction_card_view)
        private   var toUserInfo: LinearLayout=itemView.findViewById(R.id.to_user_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.itemView.setTag(list.get(position))
        holder.fromName.setText(list.get(position).getFromUser())
        holder.toName.setText(list.get(position).getToUser())
        holder.amountTransferred.setText(String.format("%d" ,list.get(position).getAmountTransferred()))


        if (list.get(position).getStatus() === 1) {
            holder.cardView.setCardBackgroundColor(Color.argb(100, 105, 187, 105))
        } else {
            holder.cardView.setCardBackgroundColor(Color.argb(100, 239, 100, 100))
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}
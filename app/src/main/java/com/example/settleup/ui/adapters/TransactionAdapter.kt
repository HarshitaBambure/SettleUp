package com.example.settleup.ui.adapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.db.entity.Expense

class TransactionAdapter (): RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
var transactions= mutableListOf<Expense>()

    fun updateData(data:List<Expense>){
        transactions.clear()
        transactions.addAll(data)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TransactionAdapter.ViewHolder(TextView(parent?.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = transactions[position].toString()
    }

    override fun getItemCount(): Int = transactions.count()

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

    }


}


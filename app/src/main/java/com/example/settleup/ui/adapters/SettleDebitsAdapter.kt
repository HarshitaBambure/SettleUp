package com.example.settleup.ui.adapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SettleDebitsAdapter (): RecyclerView.Adapter<SettleDebitsAdapter.ViewHolder>() {
    val debits= mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SettleDebitsAdapter.ViewHolder(TextView(parent?.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = debits[position].toString()
    }

    override fun getItemCount(): Int = debits.count()

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

    }


}



package com.example.settleup.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.R
import com.example.settleup.db.entity.Expense
import com.example.settleup.roundedCornersDrawable
import kotlinx.android.synthetic.main.item_transaction.view.*
import java.util.*

class TransactionAdapter() : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    var transactions = mutableListOf<Expense>()
    fun updateData(data: List<Expense>) {
        transactions.clear()
        transactions.addAll(data)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)

        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bindData(transactions.get(position))
    }

    override fun getItemCount(): Int = transactions.count()

    class TransactionViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: Expense) {
            itemView.txt_membername.text = "Paid by " + data.whopaidName
            itemView.txt_spend.text = "For " + data.purpose.toString()
            itemView.txt_credit.text = "$" + data.amount.toString()
            val rnd = Random()
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            itemView.txt_dp.text = data.whopaidName.substring(0, 1).toUpperCase()
            itemView.txt_dp.background = itemView.context.roundedCornersDrawable(bgColor = color)

        }
    }

}



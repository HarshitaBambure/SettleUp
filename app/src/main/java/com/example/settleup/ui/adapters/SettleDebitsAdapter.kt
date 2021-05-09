package com.example.settleup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.R
import com.example.settleup.db.entity.Expense
import com.example.settleup.ui.Debts
import kotlinx.android.synthetic.main.item_debts.view.*


class SettleDebitsAdapter() : RecyclerView.Adapter<SettleDebitsAdapter.SattleViewHolder>() {
    val debits = mutableListOf<Debts>()


    override fun getItemCount(): Int = debits.count()

    fun updateData(data: List<Debts>) {
        debits.clear()
        debits.addAll(data)
        notifyDataSetChanged()

    }

    class SattleViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: Debts) {
            itemView.txt_touserer.text = data.CurrentUser
            itemView.txt_from_user.text = data.ToWhome
            itemView.txt_amount.text = "$" + data.amount.toString().replace("-", "")
            if (data.amount >= 0) {

                itemView.img_arrow.setImageResource(R.drawable.ic_arrow_left)
            } else {
                itemView.img_arrow.setImageResource(R.drawable.ic_arrow_right)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SattleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_debts, parent, false)
        return SattleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SattleViewHolder, position: Int) {
        holder.bindData(debits.get(position))
    }

}



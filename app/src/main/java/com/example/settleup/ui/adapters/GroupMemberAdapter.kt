package com.example.settleup.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.R
import com.example.settleup.db.entity.Member
import com.example.settleup.roundedCornersDrawable
import kotlinx.android.synthetic.main.item_group_details.view.*
import kotlinx.android.synthetic.main.item_group_member.view.*
import kotlinx.android.synthetic.main.item_group_member.view.txt_later
import java.util.*


class GroupMemberAdapter() : RecyclerView.Adapter<GroupMemberAdapter.ViewHolder>() {
    var list = mutableListOf<Member>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group_member, parent, false)
        return ViewHolder(view)
    }

    fun updateData(listdata: List<Member>) {
        list.clear()
        list.addAll(listdata)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }

    override fun getItemCount(): Int = list.count()

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(member: Member) {
            itemView.txt_member_name.text = member.member_name
            itemView.txt_member_spent.text = member.amtGrp.toString()
            val rnd = Random()
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            itemView.txt_later.background = itemView.context.roundedCornersDrawable(bgColor = color)
            itemView.txt_later.text = member.member_name.substring(0, 1).toUpperCase()
        }
    }

}
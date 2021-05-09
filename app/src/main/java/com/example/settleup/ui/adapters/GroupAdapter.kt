package com.example.settleup.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.R
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.roundedCornersDrawable
import kotlinx.android.synthetic.main.item_group_details.view.*
import java.util.*

class GroupAdapter : RecyclerView.Adapter<GroupAdapter.GroupDetailsViewHolder>() {
    var listGroups = mutableListOf<GroupEntity>()
    lateinit var onclick: (data: GroupEntity) -> Unit

    open class GroupDetailsViewHolder(var onClick: (data: GroupEntity) -> Unit, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(data: GroupEntity) {
            itemView.txt_groupname.text = data.group_name
            itemView.txt_membercount.text = data.totalMember.toString() + " Members"
            itemView.txt_totalcredit.text = data.totalcredit.toString()

            itemView.txt_later.text=data.group_name.substring(0, 1).toUpperCase()
            val rnd = Random()
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            itemView.txt_later.background = itemView.context.roundedCornersDrawable(bgColor = color)
            itemView.setOnClickListener {
                onClick(data)
            }
        }
    }

    fun setData(listData: List<GroupEntity>) {
        listGroups.clear()
        listGroups.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group_details, parent, false)
        return GroupDetailsViewHolder(onclick, view)
    }

    override fun onBindViewHolder(holder: GroupDetailsViewHolder, position: Int) {
        holder.bindData(listGroups.get(position))
    }

    override fun getItemCount(): Int {
        return listGroups.size
    }
}



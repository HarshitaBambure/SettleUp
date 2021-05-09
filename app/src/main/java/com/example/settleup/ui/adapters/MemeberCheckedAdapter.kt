package com.example.settleup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.R
import com.example.settleup.db.entity.Member
import kotlinx.android.synthetic.main.checkbox_row.view.*



class MemeberCheckedAdapter : RecyclerView.Adapter<MemeberCheckedAdapter.ViewHolder>() {
    val mDataList: MutableList<Member> = ArrayList()

    public fun updateRecyclerData(list: MutableList<Member>) {
        mDataList.clear()
        mDataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.checkbox_row, parent, false)

        return ViewHolder(view)
    }

    fun getAllCheckedData(): List<Member> {
        return mDataList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mDataList.get(position))
    }

    override fun getItemCount(): Int = mDataList.size

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox = itemView.checkbox
        fun bindData(member: Member) {
            checkBox.isChecked = member.isChecked
            checkBox.text = member.member_name
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                member.isChecked = isChecked
            }
        }

    }
}

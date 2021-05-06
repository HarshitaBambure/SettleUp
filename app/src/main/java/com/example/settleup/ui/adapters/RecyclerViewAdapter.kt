package com.example.settleup.ui.adapters

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleup.Data
import com.example.settleup.R
import kotlinx.android.synthetic.main.checkbox_row.view.*

//divideamountactivity

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
    var checkBoxStateArray = SparseBooleanArray()
    val mDataList: MutableList<Data> = ArrayList()

    public fun updateRecyclerData(list : MutableList<Data>){
        mDataList.clear()
        mDataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.checkbox_row,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.isChecked = checkBoxStateArray.get(position,false)

        var data_position = mDataList.get(position).position
        holder.checkBox.text = "CheckBox $data_position"
    }

    override fun getItemCount(): Int = mDataList.size

    open inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var checkBox = itemView.checkbox
        init {
            checkBox.setOnClickListener {
                if (!checkBoxStateArray.get(adapterPosition, false)){
                    checkBox.isChecked = true
                    checkBoxStateArray.put(adapterPosition,true)
                }
                else{
                    checkBox.isChecked = false
                    checkBoxStateArray.put(adapterPosition,false)
                }
            }
        }

    }
}

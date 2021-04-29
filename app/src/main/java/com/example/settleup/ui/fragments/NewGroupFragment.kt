package com.example.settleup.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.settleup.R
import kotlinx.android.synthetic.main.activity_add_members.*
import kotlinx.android.synthetic.main.fragment_newgroup.*

class NewGroupFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_newgroup, container, false)
//        var EditText: EditText = root.findViewById(R.id.edt_gp_name)
//
//        btn_cr_group.setOnClickListener { view ->
//            Log.d("btn_cr_group", "Selected")
        // }
        val arr: MutableList<String> = mutableListOf<String>("Members")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr)
        listview_add.adapter = adapter

        btn_cr_group.setOnClickListener {
            arr.add(edt_gp_name.text.toString())
           // adapter.notifyDataSetChanged()
            edt_gp_name.setText("")

            // var im : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //im.hideSoftInputFromWindow(currentFocus?.windowToken,0)
        }
        return root

    }

    private fun <T> ArrayAdapter(
        newGroupFragment: NewGroupFragment,
        simpleDropdownItem1line: Int,
        arr: MutableList<T>
    ): ListAdapter? {
        TODO("Not yet implemented")
    }
}
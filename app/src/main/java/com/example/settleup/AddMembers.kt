package com.example.settleup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_members.*

class AddMembers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_members)
        var arr:MutableList<String> = mutableListOf<String>("Members")
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arr)
        listview_add.adapter = adapter

        btn_add.setOnClickListener{
            arr.add(edt_addmember.text.toString())
            adapter.notifyDataSetChanged()
            edt_addmember.setText("")

            var im :InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(currentFocus?.windowToken,0)
        }
    }
}
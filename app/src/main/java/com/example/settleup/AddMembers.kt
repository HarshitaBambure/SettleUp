package com.example.settleup

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.helper.Constants.PREF_KEY_GROUPNAME
import com.example.settleup.ui.viewmodel.CreateGroupViewModel
import kotlinx.android.synthetic.main.activity_add_members.*

class AddMembers : AppCompatActivity() {
    lateinit var groupname:String
    private lateinit var ViewModel : CreateGroupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_members)
        ViewModel = ViewModelProviders.of(this).get(CreateGroupViewModel::class.java)
        var arr:MutableList<String> = mutableListOf<String>("")
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arr)
        listview_add.adapter = adapter


        var intent = intent
         groupname = intent.getStringExtra(PREF_KEY_GROUPNAME).toString()


        btn_add.setOnClickListener{
            arr.add(edt_addmember.text.toString())
            adapter.notifyDataSetChanged()
            edt_addmember.setText("")

            var im :InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(currentFocus?.windowToken,0)

            }
        btn_save.setOnClickListener {
            val groupname = arr.getjson()?.let { it1 -> GroupEntity(1,groupname, it1) }
            groupname?.let { it1 -> ViewModel.insertGroup(it1) }
            finish()
        }

    }
}
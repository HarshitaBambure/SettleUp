package com.example.settleup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member
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
            val groupEntity = GroupEntity(groupname,arr.size)
               lifecycleScope.launchWhenStarted {
               groupEntity?.let { it1 -> ViewModel.insertGroup(groupEntity = it1)}
val id=ViewModel.getIdByName(groupEntity.group_name)
                arr.forEach {
                    id?.let { it1 -> Member(it,group_id = it1) }?.let { it2 -> ViewModel.insertMember(member = it2) }
                }
                finish()
            }
        }

    }
}
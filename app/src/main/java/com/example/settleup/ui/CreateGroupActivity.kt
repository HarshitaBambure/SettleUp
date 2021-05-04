package com.example.settleup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.settleup.AddMembers
import com.example.settleup.R
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.helper.Constants.PREF_KEY_GROUPNAME
import com.example.settleup.ui.viewmodel.CreateGroupViewModel
import kotlinx.android.synthetic.main.activity_create_group.*


class CreateGroupActivity : AppCompatActivity() {

    private lateinit var ViewModel : CreateGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

      //  ViewModel = ViewModelProviders.of(this).get(CreateGroupViewModel::class.java)

        btn_cr_group.setOnClickListener { view ->
            Log.d("btn_cr_group", "Selected")
            val groupname = edt_gp_name.text.toString()
            val intent= Intent(this,AddMembers::class.java)
            intent.putExtra(PREF_KEY_GROUPNAME,groupname)
            startActivity(intent)
            finish()
        }
    }
//    fun insertGroup(groupEntity: GroupEntity){
//        ViewModel.insertGroup(groupEntity)
//    }
}
package com.example.settleup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.settleup.AddMembersActivity
import com.example.settleup.R
import com.example.settleup.helper.Constants.PREF_KEY_GROUPNAME
import com.example.settleup.ui.viewmodel.CreateGroupViewModel
import kotlinx.android.synthetic.main.activity_create_group.*


class CreateGroupActivity : AppCompatActivity() {

    private lateinit var viewmodel: CreateGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

        //  ViewModel = ViewModelProviders.of(this).get(CreateGroupViewModel::class.java)

        btn_cr_group.setOnClickListener { view ->
            Log.d("btn_cr_group", "Selected")
            val groupname = edt_gp_name.text.toString()
            val intent = Intent(this, AddMembersActivity::class.java)
            intent.putExtra(PREF_KEY_GROUPNAME, groupname)
            startActivity(intent)
            finish()
        }
    }
//    fun insertGroup(groupEntity: GroupEntity){
//        ViewModel.insertGroup(groupEntity)
//    }

}
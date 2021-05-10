package com.example.settleup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.settleup.AddMembersActivity
import com.example.settleup.R
import com.example.settleup.helper.Constants.PREF_KEY_GROUPNAME
import com.example.settleup.ui.viewmodel.CreateGroupViewModel
import kotlinx.android.synthetic.main.activity_create_group.*
import kotlinx.android.synthetic.main.activity_new_expense.*


class CreateGroupActivity : AppCompatActivity() {

    private lateinit var viewmodel: CreateGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

        //  ViewModel = ViewModelProviders.of(this).get(CreateGroupViewModel::class.java)

        btn_cr_group.setOnClickListener { view ->
            if (TextUtils.isEmpty(edt_gp_name.text.toString())) {
                Toast.makeText(this, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("btn_cr_group", "Selected")
                val groupname = edt_gp_name.text.toString()
                val intent = Intent(this, AddMembersActivity::class.java)
                intent.putExtra(PREF_KEY_GROUPNAME, groupname)
                startActivity(intent)
                finish()
            }
        }
    }
//    fun insertGroup(groupEntity: GroupEntity){
//        ViewModel.insertGroup(groupEntity)
//    }

}
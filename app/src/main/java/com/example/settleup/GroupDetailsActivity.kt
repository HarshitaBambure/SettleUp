package com.example.settleup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settleup.helper.Constants
import com.example.settleup.ui.adapters.GroupMemberAdapter
import com.example.settleup.ui.adapters.SettleDebitsAdapter
import com.example.settleup.ui.adapters.TransactionAdapter
import com.example.settleup.ui.viewmodel.GroupDetailsViewModel
import kotlinx.android.synthetic.main.activity_group_details.*

class GroupDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel:GroupDetailsViewModel
    var grpId:Int=0
    lateinit var memberAdapter:GroupMemberAdapter
    lateinit var transactionAdapter:TransactionAdapter
    lateinit var sattleAdapter:SettleDebitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
        viewModel = ViewModelProviders.of(this).get(GroupDetailsViewModel::class.java)
        grpId= intent.getIntExtra(Constants.KEY_GRP_ID,0)
        member_rv.layoutManager = LinearLayoutManager(this)
       memberAdapter = GroupMemberAdapter()
        member_rv.adapter = memberAdapter

        rv_transaction.layoutManager = LinearLayoutManager(this)
       transactionAdapter = TransactionAdapter()
        rv_transaction.adapter =transactionAdapter

        rv_settledebit.layoutManager = LinearLayoutManager(this)
        sattleAdapter = SettleDebitsAdapter()
        rv_settledebit.adapter = sattleAdapter
        lifecycleScope.launchWhenStarted {
         val data=   viewModel.getMembersbyGroupid(grpId)
            data?.let { memberAdapter.updateData(it) }
            val expenses=viewModel.getExpensebyGroupid(grpId)
            expenses?.let { transactionAdapter.updateData(it) }
        }
    fab_add_exp.setOnClickListener {
        val intent= Intent(this,NewExpenseActivity::class.java)
        intent.putExtra(Constants.KEY_GRP_ID,grpId)
        startActivity(intent)
    }

    }
}

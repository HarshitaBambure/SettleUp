package com.example.settleup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.Member
import com.example.settleup.helper.Constants
import com.example.settleup.ui.adapters.MemeberCheckedAdapter
import com.example.settleup.ui.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.activity_divide_amount.*


class DivideAmountActivity : AppCompatActivity() {
    private var grpId: Int = 0
    private var paid_by: Int? = null
    private var amt: Int? = null
    private var purpose: String? = null
    private var paid_by_name: String? = null
    var listMember = ArrayList<Member>()
    lateinit var adapter: MemeberCheckedAdapter
    private lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divide_amount)//

        purpose = intent.getStringExtra(Constants.ACT_KEY_PURPOSE)
        grpId = intent.getIntExtra(Constants.KEY_GRP_ID, 0)
        amt = intent.getIntExtra(Constants.ACT_KEY_AMT, 0)
        paid_by = intent.getIntExtra(Constants.ACT_KEY_PAID_BY, 0)
        paid_by_name = intent.getStringExtra(Constants.ACT_KEY_PAID_BYNAME)
        viewModel = ViewModelProviders.of(this).get(ExpenseViewModel::class.java)
        adapter = MemeberCheckedAdapter()
        recyclerview.adapter = adapter
        lifecycleScope.launchWhenStarted {
            val data = viewModel.getMembersbyGroupid(grpId)
            data?.forEach {
                listMember.add(it)
            }
            adapter.updateRecyclerData(listMember)
        }
        adapter = MemeberCheckedAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        btn_save_amount.setOnClickListener {
            insertExpense()
        }

    }

    private fun insertExpense() {
        var forWhome = adapter.getAllCheckedData()
        val list = mutableListOf<Int>()
        forWhome.forEach {
            if (it.isChecked) {
                list.add(it.id)
            }
        }
        val expense = Expense(forwhom = list.getjson()!!, purpose = purpose!!, amount = amt?.toInt()!!, whopaid = paid_by!!, groupId = grpId, whopaidName = paid_by_name!!)
        // val expense=Expense()
        viewModel.insertExpense(expense)
        finish()
    }


}

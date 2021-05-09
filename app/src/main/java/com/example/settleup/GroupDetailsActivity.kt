package com.example.settleup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.Member
import com.example.settleup.helper.Constants
import com.example.settleup.ui.Debts
import com.example.settleup.ui.adapters.GroupMemberAdapter
import com.example.settleup.ui.adapters.SettleDebitsAdapter
import com.example.settleup.ui.adapters.TransactionAdapter
import com.example.settleup.ui.viewmodel.GroupDetailsViewModel
import kotlinx.android.synthetic.main.activity_group_details.*

class GroupDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: GroupDetailsViewModel
    private var grpId: Int = 0
    private lateinit var memberAdapter: GroupMemberAdapter
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var sattleAdapter: SettleDebitsAdapter
    private lateinit var listMember: MutableList<Member>
    private lateinit var listExpenses: MutableList<Expense>
    private var listDebts = mutableListOf<Debts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
        viewModel = ViewModelProviders.of(this).get(GroupDetailsViewModel::class.java)
        grpId = intent.getIntExtra(Constants.KEY_GRP_ID, 0)
        member_rv.layoutManager = LinearLayoutManager(this)
        memberAdapter = GroupMemberAdapter()
        member_rv.adapter = memberAdapter

        rv_transaction.layoutManager = LinearLayoutManager(this)
        transactionAdapter = TransactionAdapter()
        rv_transaction.adapter = transactionAdapter

        rv_settledebit.layoutManager = LinearLayoutManager(this)
        sattleAdapter = SettleDebitsAdapter()
        rv_settledebit.adapter = sattleAdapter
        updateUi()
        fab_add_exp.setOnClickListener {
            val intent = Intent(this, NewExpenseActivity::class.java)
            intent.putExtra(Constants.KEY_GRP_ID, grpId)
            startActivity(intent)

        }
        btn_settle.setOnClickListener {
            settleUp()
        }
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    private fun updateUi() {
        lifecycleScope.launchWhenStarted {
            val groupData = viewModel.getGroupbyId(grpId)
            txt_groupname.text = groupData?.group_name
            txt_later.text = groupData?.group_name?.substring(1, 1)
            listMember = viewModel.getMembersbyGroupid(grpId)?.toMutableList()!!

            listExpenses = viewModel.getExpensebyGroupid(grpId)?.toMutableList()!!

            calcualteMemberShare(listMember, listExpenses)
        }
    }

    fun calcualteMemberShare(listMember: MutableList<Member>, listExpenses: MutableList<Expense>) {
        val hasMapAmout = HashMap<Int, Int>()
        listExpenses.forEach {
            val expense = it
            expense.forwhom.getArray().forEach {
                val perPerson = expense.amount / expense.forwhom.getArray().size

                var totAmt: Int = if (hasMapAmout.get(it) != null) hasMapAmout.get(it)!! else 0
                if (it == expense.whopaid) {
                    hasMapAmout.put(it, totAmt + (expense.amount - perPerson))
                } else {
                    hasMapAmout.put(it, totAmt - perPerson)
                }
            }
        }

        listDebts.clear()
        val receiver = hasMapAmout.entries.find { it -> it.value > 0 }?.key
        hasMapAmout.entries.forEach {
            val entry = it
            listMember.forEach {
                if (entry.key == it.id) {
                    it.amtGrp = entry.value

                    if (entry.value < 0) {
                        if (receiver != null)
                            listDebts.add(Debts(getNameById(receiver), it.member_name, entry.value))
                    }
                }
            }
        }
        listExpenses?.let { transactionAdapter.updateData(it) }
        listMember?.let { memberAdapter.updateData(it) }
        listDebts?.let { sattleAdapter.updateData(it) }


    }

    private fun getNameById(id: Int): String {
        val member = listMember.find { it -> it.id == id }
        return if (member != null && member.member_name != null) member.member_name else ""
    }

    private fun settleUp() {
        lifecycleScope.launchWhenStarted {
            viewModel.settleUp(grpId)
            updateUi()
        }
    }

}

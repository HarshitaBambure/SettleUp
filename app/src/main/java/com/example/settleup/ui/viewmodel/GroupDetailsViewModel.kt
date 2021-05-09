package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member
import com.example.settleup.ripos.ExpenseRepository
import com.example.settleup.ripos.GroupRepository


class GroupDetailsViewModel(application: Application) : AndroidViewModel(application) {
    var UsersDatabase: UsersDatabase
    var groupRepository: GroupRepository
    var expensepository: ExpenseRepository

    init {
        UsersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!
        groupRepository = GroupRepository(UsersDatabase.UsersDao())
        expensepository = ExpenseRepository(UsersDatabase.ExpenseDao())

    }

    suspend fun getMembersbyGroupid(id: Int): List<Member>? {
        return groupRepository.getMemberListbyGroup(id)
    }

    suspend fun getExpensebyGroupid(id: Int): List<Expense>? {
        return expensepository.getExpensesbyGroup(id)
    }

    suspend fun getGroupbyId(id: Int): GroupEntity? {
        return groupRepository.getGroupById(id)
    }

    suspend fun settleUp(id: Int) {
        expensepository.SettleUp(id)
    }
}
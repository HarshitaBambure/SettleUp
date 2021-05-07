package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.Member
import com.example.settleup.getArray
import com.example.settleup.ripos.ExpenseRepository
import com.example.settleup.ripos.GroupRepository
import kotlinx.coroutines.launch
import java.security.acl.Group
import kotlin.math.exp

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    var UsersDatabase: UsersDatabase
    var groupRepository: GroupRepository
    var expenseRepository: ExpenseRepository
    init {
        UsersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!

        groupRepository = GroupRepository(UsersDatabase.UsersDao())
        expenseRepository =ExpenseRepository(UsersDatabase.UsersDao(), UsersDatabase.ExpenseDao())



    }
    fun insertExpense(expense: Expense){

        viewModelScope.launch{
            expenseRepository.insertExpense(expense)
        }

    }
    suspend fun getMembersbyGroupid(id : String): List<Member>? {

        return groupRepository.getMemberListbyGroup(id)
    }

}
package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.Expense
import com.example.settleup.getArray
import com.example.settleup.ripos.ExpenseRepository
import kotlinx.coroutines.launch
import java.security.acl.Group

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    var UsersDatabase: UsersDatabase
    var ExpenseRepository: ExpenseRepository
    init {
        UsersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!

           ExpenseRepository =
               ExpenseRepository(UsersDatabase.UsersDao(), UsersDatabase.ExpenseDao())



    }
    fun insertExpense(expense: Expense){

        viewModelScope.launch{
            ExpenseRepository.insertExpense(expense)
        }

    }
    suspend fun getMembersbyGroupid(id : Int): List<String>? {
      val group =  ExpenseRepository.getGroupbyid(id)
        val members = group?.list_member?.getArray()
        return members
    }

}
package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.dao.ExpenseDao
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member


class ExpenseRepository (var ExpenseDao : ExpenseDao? ) {

    suspend fun insertExpense(expense: Expense){
        ExpenseDao?.insert(expense)

    }

    suspend fun getExpensesbyGroup(name:Int): List<Expense>? {
return ExpenseDao?.getAllExpense(name)
    }


}
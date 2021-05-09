package com.example.settleup.ripos

import com.example.settleup.db.dao.ExpenseDao
import com.example.settleup.db.entity.Expense

class ExpenseRepository(var ExpenseDao: ExpenseDao?) {

    suspend fun insertExpense(expense: Expense) {
        ExpenseDao?.insert(expense)

    }

    suspend fun getExpensesbyGroup(name: Int): List<Expense>? {
        return ExpenseDao?.getAllExpense(name)
    }

    suspend fun SettleUp(name: Int) {
        ExpenseDao?.deleteExpenses(name)
    }


}
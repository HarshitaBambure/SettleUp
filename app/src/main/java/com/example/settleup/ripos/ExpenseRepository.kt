package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.Expense


class ExpenseRepository (var roomDatabase: UsersDatabase) {

    suspend fun insertExpense(expense: Expense){
        roomDatabase.ExpenseDao()?.insert(expense)
    }
}
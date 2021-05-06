package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.dao.ExpenseDao
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member


class ExpenseRepository (var UsersDao: UsersDao?,var ExpenseDao : ExpenseDao? ) {

    suspend fun insertExpense(expense: Expense){
        ExpenseDao?.insert(expense)

    }
    suspend fun getGroupbyid(id : Int): GroupEntity? {

       return UsersDao?.getGroupbyid(id)

    }
    suspend fun getMemberbyGroupid(id : Int): List<Member>? {

        return UsersDao?.getMemberbyGroupid(id)

    }


}
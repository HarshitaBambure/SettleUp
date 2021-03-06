package com.example.settleup.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.settleup.db.entity.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense where groupId=:id")
    fun getAllExpense(id: Int): List<Expense>

    @Insert
    suspend fun insert(group: Expense)

    @Query("DELETE FROM expense where groupId=:id")
    fun deleteExpenses(id: Int)
}
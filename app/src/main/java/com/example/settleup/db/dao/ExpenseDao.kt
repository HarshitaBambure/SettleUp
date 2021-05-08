package com.example.settleup.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.settleup.db.entity.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense where groupId=:id")
    fun getAllExpense(id:Int): List<Expense>

    @Insert
    suspend fun insert ( group: Expense)

    @Delete
    fun delete(group: Expense)
}
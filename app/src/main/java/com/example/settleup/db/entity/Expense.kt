package com.example.settleup.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Expense(var purpose: String,var whopaid: Int,var amount: Int, var forwhom: String,var groupId:Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
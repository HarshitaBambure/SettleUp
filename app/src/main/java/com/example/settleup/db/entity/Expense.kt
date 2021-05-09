package com.example.settleup.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Expense(var purpose: String, var whopaid: Int, var whopaidName: String, var amount: Int, var forwhom: String, var groupId: Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
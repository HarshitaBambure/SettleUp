package com.example.settleup.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class GroupEntity(var group_name: String, var totalMember: Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @Ignore
    var totalcredit = 0
}
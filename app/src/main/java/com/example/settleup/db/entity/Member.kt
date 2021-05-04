package com.example.settleup.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Member(var member_name: String, var group_name : String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}


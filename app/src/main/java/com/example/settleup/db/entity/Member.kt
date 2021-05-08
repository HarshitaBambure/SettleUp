package com.example.settleup.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
@Entity
class Member(var member_name: String, var group_id : Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    @Ignore
    var isChecked=true
}


package com.example.settleup.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GroupEntity(var group_name: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
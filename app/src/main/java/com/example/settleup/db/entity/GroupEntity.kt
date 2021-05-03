package com.example.settleup.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GroupEntity")
class GroupEntity {

     constructor(group_id: Int, group_name: String, list_member:String) {
        this.group_id = group_id
        this.group_name = group_name
        this.list_member = list_member
       }

    @PrimaryKey (autoGenerate = true)
    val group_id : Int
    @ColumnInfo(name = "group_name")
    val group_name:String
    @ColumnInfo(name = "list_member")
    val list_member:String
    }
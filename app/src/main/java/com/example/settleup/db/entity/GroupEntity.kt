package com.example.settleup.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GroupEntity")
class GroupEntity {

     constructor(id: Int, group_name: String, list_member:String) {
        this.id = id
        this.group_name = group_name
        this.list_member = list_member
       }

    @PrimaryKey (autoGenerate = true)
    val id : Int
    @ColumnInfo(name = "group_name")
    val group_name:String
    @ColumnInfo(name = "list_member")
    val list_member:String
    }
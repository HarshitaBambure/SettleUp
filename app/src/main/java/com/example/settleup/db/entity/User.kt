package com.example.settleup.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User{
    constructor(id: Int, email: String, name: String, token: String) {
        this.id = id
        this.email = email
        this.name = name
        this.token = token
    }

    @PrimaryKey (autoGenerate = true)
    val id : Int
    @ColumnInfo(name = "email")
    val email:String
    @ColumnInfo(name = "user_name")
    val name:String
    @ColumnInfo(name = "token")
    val token:String

}


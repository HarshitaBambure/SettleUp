package com.example.settleup.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(var email: String, var name: String, var token: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

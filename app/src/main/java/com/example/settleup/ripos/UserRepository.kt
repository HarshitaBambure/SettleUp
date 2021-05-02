package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.User

class UserRepository(var roomDatabase: UsersDatabase) {

    suspend fun insertUser(user: User){
        roomDatabase.UsersDao()?.insert(user)
    }
}
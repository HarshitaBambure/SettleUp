package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.GroupEntity

class GroupRepository(var roomDatabase: UsersDatabase) {
    suspend fun insertGroup(groupEntity: GroupEntity){
        roomDatabase.UsersDao()?.insert(groupEntity)
    }
}
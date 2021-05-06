package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member

class GroupRepository(var roomDatabase: UsersDatabase) {
    suspend fun insertGroup(groupEntity: GroupEntity): Long? {
      return roomDatabase.UsersDao()?.insert(groupEntity)
    }

    suspend fun insertMember(member: Member): Unit? {
        return roomDatabase.UsersDao()?.insertMember(member)
    }
    suspend fun getGroupList(): List<GroupEntity>? {
        return roomDatabase?.UsersDao()?.getAllGroups()
    }
}
package com.example.settleup.ripos

import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member

class GroupRepository(var userDao: UsersDao?) {
    suspend fun insertGroup(groupEntity: GroupEntity): Long? {
      return userDao?.insert(groupEntity)
    }

    suspend fun insertMember(member: Member): Unit? {
        return userDao?.insertMember(member)
    }
    suspend fun getGroupList(): List<GroupEntity>? {
        return userDao?.getAllGroups()
    }
    suspend fun  getMemberListbyGroup(name:String): List<Member>? {
        return userDao?.getMemberbyGroupid(name)
    }
}
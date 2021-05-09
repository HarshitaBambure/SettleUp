package com.example.settleup.ripos

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

    suspend fun getMemberListbyGroup(name: Int): List<Member>? {
        return userDao?.getMemberbyGroupid(name)
    }

    suspend fun getGroupIdByName(name: String): Int? {
        return userDao?.getGroupid(name)
    }

    suspend fun getGroupById(name: Int): GroupEntity? {
        val list = userDao?.getGroupbyid(name)
        if (list?.size!! > 0) {
            return list.get(0)
        }
        return GroupEntity("test", 0)
    }
}
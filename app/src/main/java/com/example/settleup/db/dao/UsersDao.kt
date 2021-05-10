package com.example.settleup.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member
import com.example.settleup.db.entity.User

@Dao
interface UsersDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Insert
    suspend fun insert(user: User)

    @Delete
    fun delete(user: User)

    //groupentity
    @Insert
    suspend fun insert(groupEntity: GroupEntity): Long

    @Query("SELECT * FROM GroupEntity")
    fun getAllGroups(): List<GroupEntity>

    @Delete
    fun delete(groupEntity: GroupEntity)

    @Query("SELECT * FROM GroupEntity where id =:id")
    fun getGroupbyid(id: Int): List<GroupEntity>

    //member
    @Insert
    suspend fun insertMember(member: Member)

    @Query("SELECT * FROM Member where group_id =:name")
    fun getMemberbyGroupid(name: Int): List<Member>

    @Query("SELECT id FROM GroupEntity where group_name =:name")
    fun getGroupid(name: String): Int

    @Query("SELECT id FROM Member where member_name =:name AND group_id=:grpId")
    fun getidFromMemberName(name: String, grpId: Int): Int

    @Query("DELETE FROM GroupEntity where id =:id")
    fun deleteGroup(id: Int)


}
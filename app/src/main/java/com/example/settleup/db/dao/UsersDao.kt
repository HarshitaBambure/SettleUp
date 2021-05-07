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
   suspend fun insert ( user: User)

    @Delete
   fun delete(user:User)
//groupentity
    @Insert
    suspend fun insert (groupEntity: GroupEntity):Long

    @Query("SELECT * FROM GroupEntity")
    fun getAllGroups(): List<GroupEntity>

    @Delete
    fun delete(groupEntity:GroupEntity)

    @Query("SELECT * FROM GroupEntity where id =:id")
    fun getGroupbyid(id:Int): GroupEntity

 //member
    @Insert
    suspend fun insertMember (member: Member )

    @Query("SELECT * FROM Member where group_name =:name")
    fun getMemberbyGroupid(name:String): List<Member>

}
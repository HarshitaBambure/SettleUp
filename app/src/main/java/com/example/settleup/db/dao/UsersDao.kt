package com.example.settleup.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.User

@Dao
interface UsersDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Insert
   suspend fun insert ( user: User)

    @Delete
   fun delete(user:User)

    @Insert
    suspend fun insert (groupEntity: GroupEntity)

    @Query("SELECT * FROM GroupEntity")
    fun getAllGroups(): List<GroupEntity>

    @Delete
    fun delete(groupEntity:GroupEntity)

}
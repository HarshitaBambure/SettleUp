package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member
import com.example.settleup.ripos.GroupRepository
import kotlinx.coroutines.launch

class CreateGroupViewModel(application: Application) : AndroidViewModel(application) {
    var UsersDatabase: UsersDatabase
    var GroupRepository: GroupRepository

    init {
        UsersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!
        GroupRepository = GroupRepository(UsersDatabase.UsersDao())
    }

    suspend fun insertGroup(groupEntity: GroupEntity): Long? {

           return GroupRepository.insertGroup(groupEntity)
            }
    suspend fun insertMember(member: Member): Unit? {

        return GroupRepository.insertMember(member)
    }
  /*   fun getMemberList(groupid: Int): Unit? {

        return GroupRepository.insertMember(member)
    }*/


}
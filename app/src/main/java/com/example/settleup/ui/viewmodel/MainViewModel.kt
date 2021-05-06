package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.ripos.GroupRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var usersDatabase: UsersDatabase
    var groupRepository: GroupRepository
    init {
        usersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!
        groupRepository = GroupRepository(usersDatabase)
    }
    suspend fun getGroupList(): List<GroupEntity>? {
      return  groupRepository.getGroupList()
    }
}
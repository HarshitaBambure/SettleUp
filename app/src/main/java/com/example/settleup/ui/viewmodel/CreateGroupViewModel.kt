package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.ripos.GroupRepository
import kotlinx.coroutines.launch

class CreateGroupViewModel(application: Application) : AndroidViewModel(application) {
    var UsersDatabase: UsersDatabase
    var GroupRepository: GroupRepository

    init {
        UsersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!
        GroupRepository = GroupRepository(UsersDatabase)
    }

    fun insertGroup(groupEntity: GroupEntity) {

        viewModelScope.launch {
            GroupRepository.insertGroup(groupEntity)
        }
    }
}
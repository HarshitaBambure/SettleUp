package com.example.settleup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.example.settleup.db.UsersDatabase
import com.example.settleup.db.entity.User
import com.example.settleup.ripos.UserRepository
import kotlinx.coroutines.launch

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    var UsersDatabase: UsersDatabase
    var UserRepository: UserRepository

    init {
        UsersDatabase = com.example.settleup.db.UsersDatabase.getAppDatabase((getApplication()))!!
        UserRepository = UserRepository(UsersDatabase)


    }

    fun insertUser(user: User) {

        viewModelScope.launch {
            UserRepository.insertUser(user)
        }

    }
}
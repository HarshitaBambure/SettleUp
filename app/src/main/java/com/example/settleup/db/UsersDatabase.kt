package com.example.settleup.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.settleup.db.dao.ExpenseDao
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.Expense
import com.example.settleup.db.entity.GroupEntity
import com.example.settleup.db.entity.Member
import com.example.settleup.db.entity.User
import java.security.acl.Group


@Database(entities = [User::class, GroupEntity::class, Expense::class, Member::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun UsersDao(): UsersDao?
    abstract fun ExpenseDao(): ExpenseDao?

    companion object {
        private var INSTANCE: UsersDatabase?= null


        fun getAppDatabase(context: Context): UsersDatabase? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<UsersDatabase>(
                    context.applicationContext, UsersDatabase::class.java, "SettleUp.db",
                )
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }

    }
}
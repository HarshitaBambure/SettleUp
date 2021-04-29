package com.example.settleup.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.settleup.db.dao.UsersDao
import com.example.settleup.db.entity.User


@Database(entities = [User::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {


    abstract fun UsersDao(): UsersDao?

    companion object {
        private var INSTANCE: UsersDatabase?= null

        val migration_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
            }
        }

        fun getAppDatabase(context: Context): UsersDatabase? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<UsersDatabase>(
                    context.applicationContext, UsersDatabase::class.java, "SettleUp.db",
                )
                    .addMigrations(migration_1_2)
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }

    }
}
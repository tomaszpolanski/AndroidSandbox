package com.tomaszpolanski.androidsandbox.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tomaszpolanski.androidsandbox.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProvider(): UserProvider
}
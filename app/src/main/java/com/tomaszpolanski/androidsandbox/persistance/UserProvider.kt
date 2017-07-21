package com.tomaszpolanski.androidsandbox.persistance

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.tomaszpolanski.androidsandbox.model.User
import io.reactivex.Flowable

@Dao
interface UserProvider {

    @Query("SELECT * FROM user")
    fun getAllAuthor(): Flowable<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
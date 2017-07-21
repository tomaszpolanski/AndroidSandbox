package com.tomaszpolanski.androidsandbox.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(@PrimaryKey(autoGenerate = true) val id: Int,
                val name: String)
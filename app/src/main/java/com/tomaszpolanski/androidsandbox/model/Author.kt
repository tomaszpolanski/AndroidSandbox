package com.tomaszpolanski.androidsandbox.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Author(@PrimaryKey val authorId: String)
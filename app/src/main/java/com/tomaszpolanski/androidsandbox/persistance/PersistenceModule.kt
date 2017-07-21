package com.tomaszpolanski.androidsandbox.persistance

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

  @Provides
  @Singleton
  internal fun provideDatabase(application: Application): AppDatabase =
    Room.databaseBuilder(application, AppDatabase::class.java, "main-database")
      .build()

}

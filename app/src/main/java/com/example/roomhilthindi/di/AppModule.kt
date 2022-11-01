package com.example.roomhilthindi.di

import android.content.Context
import androidx.room.Room
import com.example.roomhilthindi.userDao.UseerDao
import com.example.roomhilthindi.db.UserDatabase
import com.example.roomhilthindi.repository.UserRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UseerDao = userDatabase.userDao()


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "userDatabase").build()

    @Provides
    fun providesUserRepository(userDao: UseerDao): UserRepository = UserRepository(userDao)
}
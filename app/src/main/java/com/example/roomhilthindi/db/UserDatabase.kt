package com.example.roomhilthindi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomhilthindi.userDao.UseerDao
import com.example.roomhilthindi.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao():UseerDao




}
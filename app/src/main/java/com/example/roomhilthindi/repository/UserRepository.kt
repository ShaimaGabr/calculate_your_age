package com.example.roomhilthindi.repository

import androidx.annotation.WorkerThread
import com.example.roomhilthindi.userDao.UseerDao
import com.example.roomhilthindi.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor (private val userDao: UseerDao) {


    val getUserData:Flow<List<User>> = userDao.getUserData()

   // @WorkerThread
    suspend fun insert(user: User) = withContext(Dispatchers.IO)
   {
        userDao.insert(user)
    }
    suspend fun deletbyid(user: User)= withContext(Dispatchers.IO)
    {
        userDao.deletbyid(user)
    }
}
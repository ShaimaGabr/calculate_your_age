package com.example.roomhilthindi.userDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomhilthindi.model.User
import kotlinx.coroutines.flow.Flow
@Dao
interface UseerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUserData(): Flow<List<User>>

    @Delete
    suspend fun deletbyid(user: User)
}
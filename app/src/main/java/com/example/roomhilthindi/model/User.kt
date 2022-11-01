package com.example.roomhilthindi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (val name:String, val year:Int,val months:Int,val days:Int) {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}

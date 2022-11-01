package com.example.roomhilthindi.Utl

import android.content.Context
import android.content.SharedPreferences


class PrefManager internal constructor(context: Context) {
    var context: Context

    init {
        this.context = context
    }

    fun saveName(name: String?) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("Details", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Name", name)
        editor.commit()
    }

    fun getName(): String?
         {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("Details", Context.MODE_PRIVATE)
            return sharedPreferences.getString("Name", "")
        }
}
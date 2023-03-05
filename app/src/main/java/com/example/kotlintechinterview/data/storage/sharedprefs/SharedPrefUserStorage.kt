package com.example.kotlintechinterview.data.storage.sharedprefs

import android.content.Context
import com.example.kotlintechinterview.data.storage.UserStorage
import com.example.kotlintechinterview.data.storage.models.User

private const val SHARE_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val KEY_DEFAULT_LAST_NAME = "defaultName"
private const val KEY_DEFAULT_FIRST_NAME = "defaultName"

class SharedPrefUserStorage (context : Context): UserStorage {

    private val sharedPref = context.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(user : User): Boolean {
        sharedPref.edit().putString(KEY_FIRST_NAME, user.firstName).apply()
        sharedPref.edit().putString(KEY_LAST_NAME, user.lastName).apply()
        return true
    }

    override fun get(): User {
        val firstName = sharedPref.getString(KEY_FIRST_NAME, KEY_DEFAULT_FIRST_NAME) ?: KEY_DEFAULT_FIRST_NAME
        val lastName = sharedPref.getString(KEY_LAST_NAME, KEY_DEFAULT_LAST_NAME) ?: KEY_DEFAULT_LAST_NAME
        return User(firstName = firstName, lastName = lastName)
    }
}
package com.example.kotlintechinterview.data

import android.content.Context
import com.example.kotlintechinterview.domain.repository.UserRepository
import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam
import com.example.kotlintechinterview.domain.useCase.models.UserName

private const val SHARE_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val KEY_DEFAULT_NAME = "defaultName"

class UserRepositoryImpl (context : Context): UserRepository {

    private val sharedPref = context.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveName (saveParam : SaveUserNameParam) : Boolean{
        sharedPref.edit().putString(KEY_FIRST_NAME, saveParam.name).apply()
        return true
    }

    override fun getName () : UserName{

        val firstName = sharedPref.getString(KEY_FIRST_NAME, "") ?: ""
        val lastName = sharedPref.getString(KEY_LAST_NAME, KEY_DEFAULT_NAME) ?: KEY_DEFAULT_NAME
        return UserName(firstName = firstName, lastName = lastName)
    }
}
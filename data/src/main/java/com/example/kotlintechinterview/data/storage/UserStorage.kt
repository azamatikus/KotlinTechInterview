package com.example.kotlintechinterview.data.storage

import com.example.kotlintechinterview.data.storage.models.User

interface UserStorage {

    fun save (user : User) : Boolean

    fun get () : User
}
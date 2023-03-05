package com.example.kotlintechinterview.data.repository

import com.example.kotlintechinterview.data.storage.network.NetworkApi
import com.example.kotlintechinterview.data.storage.models.User
import com.example.kotlintechinterview.data.storage.UserStorage
import com.example.kotlintechinterview.domain.repository.UserRepository
import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam
import com.example.kotlintechinterview.domain.useCase.models.UserName

class UserRepositoryImpl (private val userStorage : UserStorage): UserRepository {

    override fun saveName (saveParam : SaveUserNameParam) : Boolean{
        return userStorage.save(mapToStorage(saveParam))
    }

    override fun getName () : UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToStorage (saveParam : SaveUserNameParam) : User {
        return User(firstName = saveParam.name, lastName = "")
    }

    private fun mapToDomain (user : User) : UserName {
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}
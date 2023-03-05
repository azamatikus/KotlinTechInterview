package com.example.kotlintechinterview.domain.repository

import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam
import com.example.kotlintechinterview.domain.useCase.models.UserName

interface UserRepository {

    fun saveName (saveParam : SaveUserNameParam) : Boolean

    fun getName () : UserName
}
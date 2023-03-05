package com.example.kotlintechinterview.domain.useCase

import com.example.kotlintechinterview.domain.repository.UserRepository
import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam

class SaveUserNameUseCase (private val userRepository: UserRepository){

    fun execute(param: SaveUserNameParam): Boolean {

        val oldUserName = userRepository.getName()
        if (oldUserName.firstName == param.name){
            return true
        }
        return userRepository.saveName(saveParam = param)
    }
}
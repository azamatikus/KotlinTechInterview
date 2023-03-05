package com.example.kotlintechinterview.domain.useCase

import com.example.kotlintechinterview.domain.repository.UserRepository
import com.example.kotlintechinterview.domain.useCase.models.UserName

class GetUserNameUseCase (val userRepository : UserRepository){

    fun execute() : UserName{
        return userRepository.getName()
    }
}
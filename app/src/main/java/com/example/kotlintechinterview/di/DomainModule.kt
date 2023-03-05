package com.example.kotlintechinterview.di

import com.example.kotlintechinterview.domain.useCase.GetUserNameUseCase
import com.example.kotlintechinterview.domain.useCase.SaveUserNameUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetUserNameUseCase>{
        GetUserNameUseCase(userRepository = get())
    }

    factory<SaveUserNameUseCase>{
        SaveUserNameUseCase(userRepository = get())
    }
}
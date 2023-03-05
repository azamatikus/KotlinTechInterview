package com.example.kotlintechinterview.di

import com.example.kotlintechinterview.data.repository.UserRepositoryImpl
import com.example.kotlintechinterview.data.storage.UserStorage
import com.example.kotlintechinterview.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.kotlintechinterview.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage>{
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository>{
        UserRepositoryImpl(userStorage = get())
    }
}
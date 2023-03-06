package com.example.kotlintechinterview.domain.useCase

import com.example.kotlintechinterview.domain.repository.UserRepository
import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam
import com.example.kotlintechinterview.domain.useCase.models.UserName
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

private const val TEST_USER_FIRST_NAME = "test first name"
private const val TEST_USER_LAST_NAME = "test last name"

class SaveUserNameUseCaseTest {

    private val userRepository = mock<UserRepository>()

    @AfterEach
    fun tearDown(){
        Mockito.reset(userRepository)

    }

    @Test
    fun `should not save data if name already saved`(){

        val testUserName = UserName(firstName = TEST_USER_FIRST_NAME, lastName = TEST_USER_LAST_NAME)
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        val testParams = SaveUserNameParam(name =  "test first name")

        val actual = useCase.execute(testParams)
        val expected = true

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.never()).saveName(saveParam = any())

    }

    @Test
    fun `should return true if save was successful`(){

        val testUserName = UserName(firstName = TEST_USER_FIRST_NAME, lastName = TEST_USER_LAST_NAME)
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val expected = true
        val testParams = SaveUserNameParam(name =  "new first name")
        Mockito.`when`(userRepository.saveName(testParams)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)

        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveName(saveParam = testParams)
    }

    @Test
    fun `should return false if save was successful`(){

        val testUserName = UserName(firstName = TEST_USER_FIRST_NAME, lastName = TEST_USER_LAST_NAME)
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val expected = false
        val testParams = SaveUserNameParam(name =  "new first name")
        Mockito.`when`(userRepository.saveName(testParams)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)

        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveName(saveParam = testParams)
    }
}
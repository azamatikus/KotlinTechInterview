package com.example.kotlintechinterview.domain.useCase

import com.example.kotlintechinterview.domain.repository.UserRepository
import com.example.kotlintechinterview.domain.useCase.models.UserName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

private const val TEST_USER_FIRST_NAME = "test first name"
private const val TEST_USER_LAST_NAME = "test last name"

class GetUserNameUseCaseTest {

    val userRepository = mock<UserRepository>()

    @Test
    fun `should return the same data as in repository` () {

        val useCase = GetUserNameUseCase(userRepository = userRepository)
        val testUserName = UserName(firstName = TEST_USER_FIRST_NAME, lastName = TEST_USER_LAST_NAME)

        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val actual = useCase.execute()
        val expected = UserName(firstName = TEST_USER_FIRST_NAME, lastName = TEST_USER_LAST_NAME)
        Assertions.assertEquals(expected, actual)
    }
}
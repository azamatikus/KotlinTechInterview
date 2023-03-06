package com.example.kotlintechinterview.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlintechinterview.domain.useCase.GetUserNameUseCase
import com.example.kotlintechinterview.domain.useCase.SaveUserNameUseCase
import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam
import com.example.kotlintechinterview.domain.useCase.models.UserName
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.mock

private const val TEST_USER_FIRST_NAME = "test first name"
private const val TEST_USER_LAST_NAME = "test last name"

class MainViewModelTest {

    private val getUserNameUseCase = mock<GetUserNameUseCase>()
    private val saveUserNameUseCase = mock<SaveUserNameUseCase>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
    }

    @BeforeEach
    fun beforeEach() {
    }

    @Test
    fun `should save user name and return true`() {

        val saveResult = true
        val testSaveText = TEST_USER_FIRST_NAME
        val testParams = SaveUserNameParam(name = testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams))
            .thenReturn(saveResult)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.save(text = testSaveText)

        val expected = "Save result = true"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1)).execute(param = testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should save user name and return false`() {
        val saveResult = false
        val testSaveText =  TEST_USER_FIRST_NAME
        val testParams = SaveUserNameParam(name = testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams))
            .thenReturn(saveResult)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.save(text = testSaveText)

        val expected = "Save result = false"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1)).execute(param = testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should load user name`() {

        val testUserName = UserName(firstName = TEST_USER_FIRST_NAME, lastName = TEST_USER_LAST_NAME)

        Mockito.`when`(getUserNameUseCase.execute()).thenReturn(testUserName)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.load()

        val expected = "${testUserName.firstName} ${testUserName.lastName}"
        val actual = viewModel.resultLive.value

        Mockito.verify(getUserNameUseCase, Mockito.times(1)).execute()
        Assertions.assertEquals(expected, actual)

    }
}
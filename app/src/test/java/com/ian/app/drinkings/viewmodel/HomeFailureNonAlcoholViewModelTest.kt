package com.ian.app.drinkings.viewmodel

import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.NonAlcoholBeverageRepository
import com.ian.app.drinkings.state.UiHomeState
import com.ian.app.drinkings.util.MainCoroutineScopeRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeFailureNonAlcoholViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainCoroutineScopeRule()

    @Mock
    private lateinit var nonAlcoholBeverageRepository: NonAlcoholBeverageRepository

    private val domainData = "error"

    private lateinit var sut: HomeNonAlcoholViewModel

    @Before
    fun setup() = runTest {
        // Arrange
        Mockito.`when`(nonAlcoholBeverageRepository.getNonAlcoholicDrinks())
            .thenReturn(DomainSource.Error(domainData))
        sut = HomeNonAlcoholViewModel(nonAlcoholBeverageRepository)
    }

    @Test
    fun `HomeViewModel homeNonAlcoholUiState should return Error`() = runTest {
        // Act
        val currentUiState = sut.homeNonAlcoholUiState

        // Assert
        assertEquals(UiHomeState.Error(domainData), currentUiState)
        assertNotEquals(UiHomeState.Loading, currentUiState)
    }
}

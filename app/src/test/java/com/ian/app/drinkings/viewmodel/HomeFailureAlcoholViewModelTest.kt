package com.ian.app.drinkings.viewmodel

import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.AlcoholBeverageRepository
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
class HomeFailureAlcoholViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainCoroutineScopeRule()

    @Mock
    private lateinit var alcoholBeverageRepository: AlcoholBeverageRepository

    private val domainData = "error"

    private lateinit var sut: HomeAlcoholViewModel

    @Before
    fun setup() = runTest {
        // Arrange
        Mockito.`when`(alcoholBeverageRepository.getAlcoholicDrinks())
            .thenReturn(DomainSource.Error(domainData))
        sut = HomeAlcoholViewModel(alcoholBeverageRepository)
    }

    @Test
    fun `HomeViewModel homeNonAlcoholUiState should return Error`() = runTest {
        // Act
        val currentUiState = sut.homeAlcoholUiState

        // Assert
        assertEquals(UiHomeState.Error(domainData), currentUiState)
        assertNotEquals(UiHomeState.Loading, currentUiState)
    }
}

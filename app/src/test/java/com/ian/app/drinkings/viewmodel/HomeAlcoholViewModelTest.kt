package com.ian.app.drinkings.viewmodel

import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.AlcoholBeverageRepository
import com.ian.app.drinkings.model.DummyResponse
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
class HomeAlcoholViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainCoroutineScopeRule()

    @Mock
    private lateinit var alcoholBeverageRepository: AlcoholBeverageRepository

    private lateinit var sut: HomeAlcoholViewModel

    @Before
    fun setup() = runTest {
        // Arrange
        sut = HomeAlcoholViewModel(alcoholBeverageRepository)
    }

    @Test
    fun `HomeViewModel getAlcoholData should return Success`() = runTest {
        // Act
        val domainData = listOf(DummyResponse.DUMMY_DOMAIN_ALCOHOL)
        val currentUiState = sut.homeAlcoholUiState

        assertEquals(UiHomeState.Loading, currentUiState.value)

        Mockito.`when`(alcoholBeverageRepository.getAlcoholicDrinks()).thenReturn(DomainSource.Success(domainData))

        sut.getAlcoholData()

        assertEquals(UiHomeState.Success(domainData), currentUiState.value)
        assertNotEquals(UiHomeState.Loading, currentUiState.value)
    }

    @Test
    fun `HomeViewModel getAlcoholData should return Errpr`() = runTest {
        // Act
        val domainData = "error"
        val currentUiState = sut.homeAlcoholUiState

        assertEquals(UiHomeState.Loading, currentUiState.value)

        Mockito.`when`(alcoholBeverageRepository.getAlcoholicDrinks()).thenReturn(DomainSource.Error(domainData))

        sut.getAlcoholData()

        assertEquals(UiHomeState.Error(domainData), currentUiState.value)
        assertNotEquals(UiHomeState.Loading, currentUiState.value)
    }
}

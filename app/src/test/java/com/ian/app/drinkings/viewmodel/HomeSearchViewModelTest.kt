package com.ian.app.drinkings.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HomeSearchViewModelTest {

    private lateinit var sut: HomeSearchViewModel

    @Before
    fun setup() {
        sut = HomeSearchViewModel()
    }

    @Test
    fun `HomeSearchViewModel uiSearchBeverageState test`() = runTest {
        sut.searchBeverage("1")

        sut.uiSearchBeverageState.test {
            Assert.assertEquals("1", awaitItem())

            sut.searchBeverage("3")
            Assert.assertEquals("3", awaitItem())
        }
    }
}

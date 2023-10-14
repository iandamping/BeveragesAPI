package com.ian.app.drinkings.core.data.local

import app.cash.turbine.test
import com.ian.app.drinkings.core.data.local.dao.NonAlcoholDrinkDao
import com.ian.app.drinkings.model.DummyResponse
import com.ian.app.drinkings.util.mockAny
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NonAlcoholLocalDataSourceImplTest {

    @Mock
    private lateinit var dao: NonAlcoholDrinkDao

    private lateinit var sut: NonAlcoholLocalDataSource

    @Before
    fun setUp() {
        sut = NonAlcoholLocalDataSourceImpl(dao)
    }

    @Test
    fun `NonAlcoholLocalDataSource loadAllNonAlcoholDrinkData should return value`() = runTest {
        val data = listOf(DummyResponse.DUMMY_ENTITY_NON_ALCOHOL)
        Mockito.`when`(dao.loadAllNonAlcoholDrinkData()).thenReturn(flowOf(data))

        val result = sut.loadAllNonAlcoholDrinkData()

        result.test {
            val state = awaitItem()
            assertEquals(data, state)
            awaitComplete()
        }
        Mockito.verify(dao, Mockito.times(1)).loadAllNonAlcoholDrinkData()
    }

    @Test
    fun `NonAlcoholLocalDataSource loadAllNonAlcoholDrinkDataById should return value`() = runTest {
        val data = DummyResponse.DUMMY_ENTITY_NON_ALCOHOL
        Mockito.`when`(dao.loadAllNonAlcoholDrinkDataById(mockAny())).thenReturn(flowOf(data))

        val result = sut.loadAllNonAlcoholDrinkDataById(1)

        result.test {
            val state = awaitItem()
            assertEquals(data, state)
            awaitComplete()
        }
        Mockito.verify(dao, Mockito.times(1)).loadAllNonAlcoholDrinkDataById(mockAny())
    }

    @Test
    fun `NonAlcoholLocalDataSource updateNonAlcoholDrinkData should run once`() = runTest {
        val data = DummyResponse.DUMMY_ENTITY_NON_ALCOHOL

        sut.updateNonAlcoholDrinkData(data)

        Mockito.verify(dao, Mockito.times(1)).updateNonAlcoholDrinkData(mockAny())
    }

    @Test
    fun `NonAlcoholLocalDataSource deleteAllNonAlcoholDrinkData should run once`() = runTest {
        sut.deleteAllNonAlcoholDrinkData()

        Mockito.verify(dao, Mockito.times(1)).deleteAllNonAlcoholDrinkData()
    }
}

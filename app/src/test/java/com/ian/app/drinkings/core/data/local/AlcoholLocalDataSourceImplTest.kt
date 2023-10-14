package com.ian.app.drinkings.core.data.local

import app.cash.turbine.test
import com.ian.app.drinkings.core.data.local.dao.AlcoholDrinkDao
import com.ian.app.drinkings.model.DummyResponse.DUMMY_ENTITY_ALCOHOL
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
class AlcoholLocalDataSourceImplTest {

    @Mock
    private lateinit var dao: AlcoholDrinkDao

    private lateinit var sut: AlcoholLocalDataSource

    @Before
    fun setUp() {
        sut = AlcoholLocalDataSourceImpl(dao)
    }

    @Test
    fun `AlcoholLocalDataSource loadAllAlcoholDrinkData should return value`() = runTest {
        val data = listOf(DUMMY_ENTITY_ALCOHOL)
        Mockito.`when`(dao.loadAllAlcoholDrinkData()).thenReturn(flowOf(data))

        val result = sut.loadAllAlcoholDrinkData()

        result.test {
            val state = awaitItem()
            assertEquals(data, state)
            awaitComplete()
        }
        Mockito.verify(dao, Mockito.times(1)).loadAllAlcoholDrinkData()
    }

    @Test
    fun `AlcoholLocalDataSource loadAllAlcoholDrinkDataById should return value`() = runTest {
        val data = DUMMY_ENTITY_ALCOHOL
        Mockito.`when`(dao.loadAllAlcoholDrinkDataById(mockAny())).thenReturn(flowOf(data))

        val result = sut.loadAllAlcoholDrinkDataById(1)

        result.test {
            val state = awaitItem()
            assertEquals(data, state)
            awaitComplete()
        }
        Mockito.verify(dao, Mockito.times(1)).loadAllAlcoholDrinkDataById(mockAny())
    }

    @Test
    fun `AlcoholLocalDataSource updateAlcoholDrinkData should run once`() = runTest {
        val data = DUMMY_ENTITY_ALCOHOL

        sut.updateAlcoholDrinkData(data)

        Mockito.verify(dao, Mockito.times(1)).updateAlcoholDrinkData(mockAny())
    }

    @Test
    fun `AlcoholLocalDataSource deleteAllAlcoholDrinkData should  run once`() = runTest {
        sut.deleteAllAlcoholDrinkData()

        Mockito.verify(dao, Mockito.times(1)).deleteAllAlcoholDrinkData()
    }
}

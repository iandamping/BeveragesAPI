package com.ian.app.drinkings.core.data.repository

import app.cash.turbine.test
import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.local.NonAlcoholLocalDataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSource
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.NonAlcoholBeverageRepository
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
class NonAlcoholBeverageRepositoryImplTest {

    @Mock
    private lateinit var remoteDataSource: BeverageRemoteDataSource

    @Mock
    private lateinit var localDataSource: NonAlcoholLocalDataSource

    private lateinit var sut: NonAlcoholBeverageRepository

    @Before
    fun setUp() {
        sut = NonAlcoholBeverageRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `NonAlcoholBeverageRepository getNonAlcoholicDrinks should return success`() = runTest {
        val remoteData =
            DataSource.Success(GeneralDrinkData(listOf(DummyResponse.DUMMY_NON_ALCOHOL)))
        Mockito.`when`(remoteDataSource.getNonAlcoholicDrinks()).thenReturn(remoteData)

        val result = sut.getNonAlcoholicDrinks()

        Mockito.verify(remoteDataSource, Mockito.times(1)).getNonAlcoholicDrinks()
        assertEquals(DomainSource.Success(listOf(DummyResponse.DUMMY_DOMAIN_NON_ALCOHOL)), result)
    }

    @Test
    fun `NonAlcoholBeverageRepository getAlcoholicDrinks should return error`() = runTest {
        val remoteData =
            DataSource.Error("error")
        Mockito.`when`(remoteDataSource.getNonAlcoholicDrinks()).thenReturn(remoteData)

        val result = sut.getNonAlcoholicDrinks()

        Mockito.verify(remoteDataSource, Mockito.times(1)).getNonAlcoholicDrinks()
        assertEquals(DomainSource.Error("error"), result)
    }

    @Test
    fun `NonAlcoholBeverageRepository loadAllNonAlcoholDrinkData should return success`() =
        runTest {
            val localData = listOf(DummyResponse.DUMMY_ENTITY_NON_ALCOHOL)

            Mockito.`when`(localDataSource.loadAllNonAlcoholDrinkData())
                .thenReturn(flowOf(localData))

            val result = sut.loadAllNonAlcoholDrinkData()

            Mockito.verify(localDataSource, Mockito.times(1)).loadAllNonAlcoholDrinkData()

            result.test {
                val state = awaitItem()
                assertEquals(listOf(DummyResponse.DUMMY_DOMAIN_NON_ALCOHOL), state)
                awaitComplete()
            }
        }

    @Test
    fun `NonAlcoholBeverageRepository loadAllNonAlcoholDrinkDataById should return success`() =
        runTest {
            val localData = DummyResponse.DUMMY_ENTITY_NON_ALCOHOL

            Mockito.`when`(localDataSource.loadAllNonAlcoholDrinkDataById(mockAny()))
                .thenReturn(flowOf(localData))

            val result = sut.loadAllNonAlcoholDrinkDataById(1)

            Mockito.verify(localDataSource, Mockito.times(1))
                .loadAllNonAlcoholDrinkDataById(mockAny())

            result.test {
                val state = awaitItem()
                assertEquals(DummyResponse.DUMMY_DOMAIN_NON_ALCOHOL, state)
                awaitComplete()
            }
        }

    @Test
    fun `NonAlcoholBeverageRepository updateNonAlcoholDrinkData should run once`() =
        runTest {
            sut.updateNonAlcoholDrinkData(DummyResponse.DUMMY_DOMAIN_NON_ALCOHOL)

            Mockito.verify(localDataSource, Mockito.times(1))
                .updateNonAlcoholDrinkData(mockAny())
        }

    @Test
    fun `NonAlcoholBeverageRepository deleteAllNonAlcoholDrinkData should run once`() =
        runTest {
            sut.deleteAllNonAlcoholDrinkData()

            Mockito.verify(localDataSource, Mockito.times(1))
                .deleteAllNonAlcoholDrinkData()
        }
}

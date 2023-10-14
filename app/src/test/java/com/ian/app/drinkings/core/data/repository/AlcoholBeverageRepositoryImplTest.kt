package com.ian.app.drinkings.core.data.repository

import app.cash.turbine.test
import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.local.AlcoholLocalDataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSource
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.AlcoholBeverageRepository
import com.ian.app.drinkings.model.DummyResponse
import com.ian.app.drinkings.util.mockAny
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlcoholBeverageRepositoryImplTest {

    @Mock
    private lateinit var remoteDataSource: BeverageRemoteDataSource

    @Mock
    private lateinit var localDataSource: AlcoholLocalDataSource

    private lateinit var sut: AlcoholBeverageRepository

    @Before
    fun setUp() {
        sut = AlcoholBeverageRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `AlcoholBeverageRepository getAlcoholicDrinks should return success`() = runTest {
        val remoteData =
            DataSource.Success(GeneralDrinkData(listOf(DummyResponse.DUMMY_ALCOHOL)))
        Mockito.`when`(remoteDataSource.getAlcoholicDrinks()).thenReturn(remoteData)

        val result = sut.getAlcoholicDrinks()

        Mockito.verify(remoteDataSource, Mockito.times(1)).getAlcoholicDrinks()
        Assert.assertEquals(DomainSource.Success(listOf(DummyResponse.DUMMY_DOMAIN_ALCOHOL)), result)
    }

    @Test
    fun `AlcoholBeverageRepository getAlcoholicDrinks should return error`() = runTest {
        val remoteData =
            DataSource.Error("error")
        Mockito.`when`(remoteDataSource.getAlcoholicDrinks()).thenReturn(remoteData)

        val result = sut.getAlcoholicDrinks()

        Mockito.verify(remoteDataSource, Mockito.times(1)).getAlcoholicDrinks()
        Assert.assertEquals(DomainSource.Error("error"), result)
    }

    @Test
    fun `AlcoholBeverageRepository loadAllAlcoholDrinkData should return success`() =
        runTest {
            val localData = listOf(DummyResponse.DUMMY_ENTITY_ALCOHOL)

            Mockito.`when`(localDataSource.loadAllAlcoholDrinkData())
                .thenReturn(flowOf(localData))

            val result = sut.loadAllAlcoholDrinkData()

            Mockito.verify(localDataSource, Mockito.times(1)).loadAllAlcoholDrinkData()

            result.test {
                val state = awaitItem()
                Assert.assertEquals(listOf(DummyResponse.DUMMY_DOMAIN_ALCOHOL), state)
                awaitComplete()
            }
        }

    @Test
    fun `AlcoholBeverageRepository loadAllAlcoholDrinkDataById should return success`() =
        runTest {
            val localData = DummyResponse.DUMMY_ENTITY_ALCOHOL

            Mockito.`when`(localDataSource.loadAllAlcoholDrinkDataById(mockAny()))
                .thenReturn(flowOf(localData))

            val result = sut.loadAllAlcoholDrinkDataById(1)

            Mockito.verify(localDataSource, Mockito.times(1))
                .loadAllAlcoholDrinkDataById(mockAny())

            result.test {
                val state = awaitItem()
                Assert.assertEquals(DummyResponse.DUMMY_DOMAIN_ALCOHOL, state)
                awaitComplete()
            }
        }

    @Test
    fun `AlcoholBeverageRepository updateAlcoholDrinkData should run once`() =
        runTest {
            sut.updateAlcoholDrinkData(DummyResponse.DUMMY_DOMAIN_ALCOHOL)

            Mockito.verify(localDataSource, Mockito.times(1))
                .updateAlcoholDrinkData(mockAny())
        }

    @Test
    fun `AlcoholBeverageRepository deleteAllAlcoholDrinkData should run once`() =
        runTest {
            sut.deleteAllAlcoholDrinkData()

            Mockito.verify(localDataSource, Mockito.times(1))
                .deleteAllAlcoholDrinkData()
        }
}

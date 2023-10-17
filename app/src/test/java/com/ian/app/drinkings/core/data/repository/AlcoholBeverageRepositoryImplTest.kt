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
    fun `AlcoholBeverageRepository getAlcoholicDrinksById should return success`() = runTest {
        val remoteData =
            DataSource.Success(DummyResponse.DETAIL_DUMMY_ALCOHOL)
        Mockito.`when`(remoteDataSource.getAlcoholicDrinksById(mockAny())).thenReturn(remoteData)

        val result = sut.getAlcoholicDrinksById(1)

        Mockito.verify(remoteDataSource, Mockito.times(1)).getAlcoholicDrinksById(mockAny())
        Assert.assertEquals(DomainSource.Success(DummyResponse.DETAIL_DUMMY_DOMAIN_ALCOHOL), result)
    }

    @Test
    fun `AlcoholBeverageRepository getAlcoholicDrinksById should return error`() = runTest {
        val remoteData =
            DataSource.Error("error")
        Mockito.`when`(remoteDataSource.getAlcoholicDrinksById(mockAny())).thenReturn(remoteData)

        val result = sut.getAlcoholicDrinksById(1)

        Mockito.verify(remoteDataSource, Mockito.times(1)).getAlcoholicDrinksById(mockAny())
        Assert.assertEquals(DomainSource.Error("error"), result)
    }

    @Test
    fun `AlcoholBeverageRepository loadAllAlcoholDrinkData should return success`() =
        runTest {
            val localData = listOf(DummyResponse.DETAIL_DUMMY_ENTITY_ALCOHOL)

            Mockito.`when`(localDataSource.loadAllAlcoholDrinkData())
                .thenReturn(flowOf(localData))

            val result = sut.loadDetailAllAlcoholDrinkData()

            Mockito.verify(localDataSource, Mockito.times(1)).loadAllAlcoholDrinkData()

            result.test {
                val state = awaitItem()
                Assert.assertEquals(listOf(DummyResponse.DETAIL_DUMMY_DOMAIN_ALCOHOL), state)
                awaitComplete()
            }
        }

    @Test
    fun `AlcoholBeverageRepository loadAllAlcoholDrinkDataById should return success`() =
        runTest {
            val localData = DummyResponse.DETAIL_DUMMY_ENTITY_ALCOHOL

            Mockito.`when`(localDataSource.loadAllAlcoholDrinkDataById(mockAny()))
                .thenReturn(flowOf(localData))

            val result = sut.loadDetailAllAlcoholDrinkDataById(1)

            Mockito.verify(localDataSource, Mockito.times(1))
                .loadAllAlcoholDrinkDataById(mockAny())

            result.test {
                val state = awaitItem()
                Assert.assertEquals(DummyResponse.DETAIL_DUMMY_DOMAIN_ALCOHOL, state)
                awaitComplete()
            }
        }

    @Test
    fun `AlcoholBeverageRepository updateAlcoholDrinkData should run once`() =
        runTest {
            sut.updateDetailAlcoholDrinkData(DummyResponse.DETAIL_DUMMY_DOMAIN_ALCOHOL)

            Mockito.verify(localDataSource, Mockito.times(1))
                .updateAlcoholDrinkData(mockAny())
        }

    @Test
    fun `AlcoholBeverageRepository deleteAllAlcoholDrinkData should run once`() =
        runTest {
            sut.deleteAllDetailAlcoholDrinkData()

            Mockito.verify(localDataSource, Mockito.times(1))
                .deleteAllAlcoholDrinkData()
        }
}

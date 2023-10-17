package com.ian.app.drinkings.core.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.remote.api.ApiInterface
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import com.ian.app.drinkings.model.DummyResponse
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class BeverageRemoteDataSourceImplTest {

    private lateinit var sut: BeverageRemoteDataSource

    @Mock
    private lateinit var apiInterface: ApiInterface

    @Before
    fun setUp() {
        sut = BeverageRemoteDataSourceImpl(apiInterface)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinks() return Success`() = runTest {
        val data = GeneralDrinkData(listOf(DummyResponse.DUMMY_ALCOHOL))
        Mockito.`when`(apiInterface.getAlcoholicDrinks()).thenReturn(
            Response.success(
                data
            )
        )

        val result = sut.getAlcoholicDrinks()
        Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinks()

        assertEquals(DataSource.Success(data), result)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinks() return Error`() = runTest {
        Mockito.`when`(apiInterface.getAlcoholicDrinks()).thenReturn(
            Response.error(
                404,
                "{\"message\":\"Not Found\"}".toResponseBody("text/plain".toMediaType())
            )
        )

        val result = sut.getAlcoholicDrinks()

        Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinks()
        assertEquals(
            DataSource.Error(
                "Error from retrofit Http Code is not 200 .. 300 with message : {\"message\":\"Not Found\"}"
            ),
            result
        )
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinks() return null Body`() = runTest {
        Mockito.`when`(apiInterface.getAlcoholicDrinks()).thenReturn(
            Response.success(
                null
            )
        )

        val result = sut.getAlcoholicDrinks()

        Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinks()
        assertEquals(DataSource.Error("Error from retrofit : Body null"), result)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinks() return throw IllegalArgumentException`() =
        runTest {
            Mockito.`when`(apiInterface.getAlcoholicDrinks()).thenThrow(IllegalArgumentException())

            val result = sut.getAlcoholicDrinks()

            Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinks()
            assertEquals(DataSource.Error("default error"), result)
        }

    @Test
    fun `when RemoteDataSource getNonAlcoholicDrinks() return Success`() = runTest {
        val data = GeneralDrinkData(listOf(DummyResponse.DUMMY_NON_ALCOHOL))
        Mockito.`when`(apiInterface.getNonAlcoholicDrinks()).thenReturn(
            Response.success(
                data
            )
        )

        val result = sut.getNonAlcoholicDrinks()

        Mockito.verify(apiInterface, Mockito.times(1)).getNonAlcoholicDrinks()
        assertEquals(DataSource.Success(data), result)
    }

    @Test
    fun `when RemoteDataSource getNonAlcoholicDrinks() return Error`() = runTest {
        Mockito.`when`(apiInterface.getNonAlcoholicDrinks()).thenReturn(
            Response.error(
                404,
                "{\"message\":\"Not Found\"}".toResponseBody("text/plain".toMediaType())
            )
        )

        val result = sut.getNonAlcoholicDrinks()

        Mockito.verify(apiInterface, Mockito.times(1)).getNonAlcoholicDrinks()
        assertEquals(
            DataSource.Error(
                "Error from retrofit Http Code is not 200 .. 300 with message : {\"message\":\"Not Found\"}"
            ),
            result
        )
    }

    @Test
    fun `when RemoteDataSource getNonAlcoholicDrinks() return null Body`() = runTest {
        Mockito.`when`(apiInterface.getNonAlcoholicDrinks()).thenReturn(
            Response.success(
                null
            )
        )

        val result = sut.getNonAlcoholicDrinks()

        Mockito.verify(apiInterface, Mockito.times(1)).getNonAlcoholicDrinks()
        assertEquals(DataSource.Error("Error from retrofit : Body null"), result)
    }

    @Test
    fun `when RemoteDataSource getNonAlcoholicDrinks() return throw IllegalArgumentException`() =
        runTest {
            Mockito.`when`(apiInterface.getNonAlcoholicDrinks()).thenThrow(IllegalArgumentException())

            val result = sut.getNonAlcoholicDrinks()

            Mockito.verify(apiInterface, Mockito.times(1)).getNonAlcoholicDrinks()
            assertEquals(DataSource.Error("default error"), result)
        }
}

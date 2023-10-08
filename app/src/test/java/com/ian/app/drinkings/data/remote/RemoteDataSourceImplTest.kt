package com.ian.app.drinkings.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.data.remote.api.ApiInterface
import com.ian.app.drinkings.data.remote.model.GeneralDrinkData
import com.ian.app.drinkings.model.DummyResponse
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceImplTest {

    private lateinit var sut: RemoteDataSource

    @Mock
    private lateinit var apiInterface: ApiInterface

    @Before
    fun setUp() {
        sut = RemoteDataSourceImpl(apiInterface)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinks() return Success`() = runTest {
        val data = GeneralDrinkData(DummyResponse.DUMMY_ALCOHOL)
        Mockito.`when`(apiInterface.getAlcoholicDrinks()).thenReturn(
            Response.success(
                data
            )
        )

        val result = sut.getAlcoholicDrinks()

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

        assertEquals(DataSource.Error("Error from retrofit : Body null"), result)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinks() return throw IllegalArgumentException`() =
        runTest {
            Mockito.`when`(apiInterface.getAlcoholicDrinks()).thenThrow(IllegalArgumentException())

            val result = sut.getAlcoholicDrinks()

            assertEquals(DataSource.Error("default error"), result)
        }
}

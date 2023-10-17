package com.ian.app.drinkings.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSourceImpl
import com.ian.app.drinkings.core.data.remote.api.ApiInterface
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import com.ian.app.drinkings.model.DummyResponse
import com.ian.app.drinkings.util.mockAny
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
class BeverageRemoteDataSourceImplDetailTest {

    private lateinit var sut: BeverageRemoteDataSource

    @Mock
    private lateinit var apiInterface: ApiInterface

    @Before
    fun setUp() {
        sut = BeverageRemoteDataSourceImpl(apiInterface)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinksById() return Success`() = runTest {
        val data = DummyResponse.DETAIL_DUMMY_ALCOHOL
        Mockito.`when`(apiInterface.getAlcoholicDrinksById(mockAny())).thenReturn(
            Response.success(
                GeneralDrinkData(listOf(data))
            )
        )

        val result = sut.getAlcoholicDrinksById(1)
        Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinksById(1)

        assertEquals(DataSource.Success(data), result)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinksById() return Error`() = runTest {
        Mockito.`when`(apiInterface.getAlcoholicDrinksById(mockAny())).thenReturn(
            Response.error(
                404,
                "{\"message\":\"Not Found\"}".toResponseBody("text/plain".toMediaType())
            )
        )

        val result = sut.getAlcoholicDrinksById(1)

        Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinksById(mockAny())
        assertEquals(
            DataSource.Error(
                "Error from retrofit Http Code is not 200 .. 300 with message : {\"message\":\"Not Found\"}"
            ),
            result
        )
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinksById() return null Body`() = runTest {
        Mockito.`when`(apiInterface.getAlcoholicDrinksById(mockAny())).thenReturn(
            Response.success(
                null
            )
        )

        val result = sut.getAlcoholicDrinksById(1)

        Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinksById(1)
        assertEquals(DataSource.Error("Error from retrofit : Body null"), result)
    }

    @Test
    fun `when RemoteDataSource getAlcoholicDrinksById() return throw IllegalArgumentException`() =
        runTest {
            Mockito.`when`(apiInterface.getAlcoholicDrinksById(mockAny()))
                .thenThrow(IllegalArgumentException())

            val result = sut.getAlcoholicDrinksById(1)

            Mockito.verify(apiInterface, Mockito.times(1)).getAlcoholicDrinksById(1)
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
            Mockito.`when`(apiInterface.getNonAlcoholicDrinks())
                .thenThrow(IllegalArgumentException())

            val result = sut.getNonAlcoholicDrinks()

            Mockito.verify(apiInterface, Mockito.times(1)).getNonAlcoholicDrinks()
            assertEquals(DataSource.Error("default error"), result)
        }
}

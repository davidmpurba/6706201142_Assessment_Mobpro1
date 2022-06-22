package org.d3if1142.temperature_converter.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if1142.temperature_converter.model.MyData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.github.com/users/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface DataApiInterface {

    @GET("davidmpurba")
    suspend fun  getData(): MyData

}

object DataApi{
    val service: DataApiInterface by lazy {
        retrofit.create(DataApiInterface::class.java)
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}
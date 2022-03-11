package com.example.giphy.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL =
    "https://api.giphy.com/v1/gifs/"

private const val API_KEY =
    "pkh1YxXcqvei1Y2E7cAHVCVKk9vb0Cml"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GiphyApiService {
    @GET("search?api_key=${API_KEY}&q=4Head&limit=25&offset=0&rating=r&lang=en")
    suspend fun getApiResponse(): ApiResponse

}

object GiphyApi {
    val retrofitService: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}

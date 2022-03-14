package com.example.giphy.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL =
    "https://api.giphy.com/v1/gifs/"

const val API_KEY =
    "pkh1YxXcqvei1Y2E7cAHVCVKk9vb0Cml"


interface GiphyApiService {
    @GET("search?api_key=${API_KEY}&limit=25&offset=0&rating=r&lang=en")
    suspend fun getApiResponse(@Query("q") query: String): ApiResponse

}



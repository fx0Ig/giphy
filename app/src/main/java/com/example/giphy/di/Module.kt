package com.example.giphy.di


import com.example.giphy.network.BASE_URL
import com.example.giphy.network.GiphyApiService
import com.example.giphy.overview.GifsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build().create(GiphyApiService::class.java)
    }

    single {
        GifsViewModel(get())
    }
}


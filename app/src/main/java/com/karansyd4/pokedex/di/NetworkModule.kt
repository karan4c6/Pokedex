package com.karansyd4.pokedex.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.karansyd4.pokedex.data.remote.PokedexService
import com.karansyd4.pokedex.util.Util.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
    }

    @Singleton
    @Provides
    fun providePokedexService(retrofit: Retrofit.Builder): PokedexService {
        return retrofit
            .build()
            .create(PokedexService::class.java)
    }
}
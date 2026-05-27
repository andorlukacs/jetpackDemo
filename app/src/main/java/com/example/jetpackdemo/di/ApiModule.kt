package com.example.jetpackdemo.di

import com.example.jetpackdemo.data.remote.ApiService
import com.example.jetpackdemo.data.repository.MovieRepositoryImpl
import com.example.jetpackdemo.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(Json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(impl: MovieRepositoryImpl): MovieRepository = impl
}
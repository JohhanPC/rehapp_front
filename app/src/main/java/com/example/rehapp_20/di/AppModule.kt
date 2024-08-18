package com.example.rehapp_20.di

import com.example.rehapp_20.apiservice.PhysioApiService
import com.example.rehapp_20.apiservice.UserApiService
import com.example.rehapp_20.connection.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApiService(): UserApiService {
        return RetrofitClient.userApiService
    }

    @Provides
    @Singleton
    fun providePhysioApiService(): PhysioApiService {
        return RetrofitClient.physioApiService
    }

}
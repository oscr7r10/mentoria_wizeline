package com.cursokotlin.retrofitkotlinexample.injection

import com.cursokotlin.retrofitkotlinexample.data.repository.datasource.DogsDataSource
import com.cursokotlin.retrofitkotlinexample.data.services.dogsapi.APIService
import com.cursokotlin.retrofitkotlinexample.data.services.dogsapi.DogsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @DogsRepositoryRemote
    @Singleton
    @Provides
    fun provideDogsRepositoryRemote(retrofit: Retrofit): DogsDataSource {
        return DogsRemoteDataSource(retrofit.create(APIService::class.java))
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DogsRepositoryRemote
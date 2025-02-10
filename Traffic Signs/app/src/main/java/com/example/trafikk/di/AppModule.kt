package com.example.trafikk.di

import com.example.trafikk.data.datasource.TrafikTanzimDataSource
import com.example.trafikk.data.repo.TrafikTanzimRepository
import com.example.trafikk.retrofit.ApiUtils
import com.example.trafikk.retrofit.TrafikTanzimDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTrafikTanzimDataSource(tdao:TrafikTanzimDao):TrafikTanzimDataSource {
        return TrafikTanzimDataSource(tdao)
    }

    @Provides
    @Singleton
    fun provideTrafikTanzimRepository(tds:TrafikTanzimDataSource): TrafikTanzimRepository {
        return TrafikTanzimRepository(tds)
    }

    @Provides
    @Singleton
    fun provideTrafikTanzimDao() : TrafikTanzimDao {
        return ApiUtils.getTrafikTanzimDao()
    }
}
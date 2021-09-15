package com.plcoding.cryptoinfo.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.plcoding.cryptoinfo.common.Constants
import com.plcoding.cryptoinfo.data.remote.CoinsApi
import com.plcoding.cryptoinfo.domain.repository.CoinRepository
import com.plcoding.cryptoinfo.domain.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: CoinsApi): CoinRepository {
        return CoinRepositoryImpl(apiService = apiService)
    }


    @Provides
    @Singleton
    fun provideCoinsApi(okHttpClient: OkHttpClient, gson: Gson) = Retrofit
        .Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
        .create<CoinsApi>()


    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().setLenient().create()!!

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

}

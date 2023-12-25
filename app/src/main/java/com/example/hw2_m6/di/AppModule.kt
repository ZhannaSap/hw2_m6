package com.example.hw2_m6.di

import com.example.hw2_m6.BuildConfig
import com.example.hw2_m6.data.AppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/*https://rickandmortyapi.com/api*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun  provideRetrofit(
        okHttpClient: OkHttpClient
    ) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ):OkHttpClient{
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideLogginInterseptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideAppService(
        retrofit: Retrofit
    ):AppApiService{
        return retrofit.create(AppApiService::class.java)
    }

}
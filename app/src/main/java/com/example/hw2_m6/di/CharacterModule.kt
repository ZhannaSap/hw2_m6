package com.example.hw2_m6.di

import com.example.hw2_m6.BuildConfig
import com.example.hw2_m6.data.AppApiService
import com.example.hw2_m6.data.Repository
import com.example.hw2_m6.ui.main_activity.CharacterViewModel
import com.example.hw2_m6.ui.second_activity.DetailsViewModel
import com.example.hw2_m6.ui.utils.CharacterStatusDeserializer
import com.example.hw2_m6.ui.utils.Status
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



val networkModule = module {
    single {
        provideLogginInterseptor()
    }

    single {
        provideGson()
    }

    single {
        provideOkHttpClient(get())
    }

    single {
        provideAppService(get())
    }
    factory {
        provideRetrofit(get(), get())
    }

}

val repositoryModule = module {

    single {
        Repository(get())
    }

}

val viewModelModule = module {

    viewModel {
        CharacterViewModel(get())
    }

    viewModel {
        DetailsViewModel(get())
    }

}

val characterModules = listOf(networkModule, repositoryModule, viewModelModule)

fun provideRetrofit(
    gson: Gson,
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}


fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}


fun provideLogginInterseptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideAppService(
    retrofit: Retrofit
): AppApiService {
    return retrofit.create(AppApiService::class.java)
}


fun provideGson(): Gson {
    return GsonBuilder()
        .registerTypeAdapter(Status::class.java, CharacterStatusDeserializer())
        .create()
}
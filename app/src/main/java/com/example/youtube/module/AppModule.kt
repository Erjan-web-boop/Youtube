package com.example.youtube.module

import com.example.youtube.api.ApiService
import com.example.youtube.viewmodel.YouTubeViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { YouTubeViewModel(get()) }
    single { provideRetrofit() }
    single { provideYouTubeApiService(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/")
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Name", "AIzaSyAKMouoKTljTxHeOiooPxpcOlzdLQUgrVA")
                .build()
            chain.proceed(request)
        }
        .build()
}

fun provideYouTubeApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

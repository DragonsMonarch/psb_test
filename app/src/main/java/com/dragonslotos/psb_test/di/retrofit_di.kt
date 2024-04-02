package com.dragonslotos.psb_test.di

import com.dragonslotos.psb_test.data.retrofit.Dataholder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//module for load all classe for retrofit
val retrofit_module = module {

    single<Dataholder> {
        return@single Builder()
            .baseUrl("https://www.timeapi.io/api/Time/current/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build().create(Dataholder::class.java)

    }

    single<OkHttpClient>{
        return@single OkHttpClient()
            .newBuilder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(get())
            .build()
    }

    single<Interceptor> {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return@single logging
    }


}
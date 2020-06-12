package com.apipas.mynote.di

import android.content.Context
import com.apipas.mynote.data.remote.AppOkHttpClient
import com.apipas.mynote.data.remote.client.api.AppRetrofit
import com.apipas.mynote.data.remote.service.NoteServiceApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.reflect.KClass

val remoteDataModule = module {
    factory { provideOkHttpClient(get()) }
    factory { provideApi<NoteServiceApi>(get(), NoteServiceApi::class) }

    single { provideRetrofit(get()) }
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return AppRetrofit(okHttpClient).getRetrofitBuilder().build()
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    return AppOkHttpClient(
        context
    ).getDefaultOkHttpClientBuilder().build()
}


fun <T> provideApi(retrofit: Retrofit, cls: KClass<*>): T {
    return retrofit.create(cls.java) as T
}
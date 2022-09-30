package com.example.contacttracingproject.di

import com.example.contacttracingproject.data.UserRepository
import com.example.contacttracingproject.data.UserRepositoryImpl
import com.example.contacttracingproject.history.HistoryRepository
import com.example.contacttracingproject.network.RetrofitUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    private const val baseUrl = "https://corolo-retrofit.herokuapp.com/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun getInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFunctions(retrofit: Retrofit): RetrofitUser {
        return retrofit.create(RetrofitUser::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(retrofitUser: RetrofitUser) =
        UserRepositoryImpl(retrofitUser)
}



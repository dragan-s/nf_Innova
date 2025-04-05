package com.dragan.androidtestapp.di

import com.dragan.androidtestapp.repository.GithubApi
import com.dragan.androidtestapp.repository.remote.GithubApiImpl
import com.dragan.androidtestapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Singleton
    @Provides
    fun provideGithubRepository(
        api: GithubApi
    ) = GithubApiImpl(api)


    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubApi(client: OkHttpClient)  : GithubApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(GithubApi::class.java)
    }
}
package com.dragan.androidtestapp.di

import com.dragan.androidtestapp.data.remote.GithubApi
import com.dragan.androidtestapp.repository.GithubRepository
import com.dragan.androidtestapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    ) = GithubRepository(api)


    @Singleton
    @Provides
    fun provideGithubApi()  : GithubApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GithubApi::class.java)
    }
}
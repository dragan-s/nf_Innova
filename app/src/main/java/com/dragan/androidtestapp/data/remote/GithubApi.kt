package com.dragan.androidtestapp.data.remote

import com.dragan.androidtestapp.data.remote.responses.Repo
import com.dragan.androidtestapp.data.remote.responses.RepoDetails
import com.dragan.androidtestapp.data.remote.responses.Tag
import com.dragan.androidtestapp.data.remote.responses.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{name}")
    suspend fun getUser(@Path("name") name: String)  : User

    @GET("users/{name}/repos")
    suspend fun getUserRepos(@Path("name") name : String) : List<Repo>

    @GET("repos/{name}/{repo}")
    suspend fun getRepoDetails(
        @Path("name") name : String,
        @Path("repo") repo : String
    ) : RepoDetails

    @GET("repos/{name}/{repo}/tags")
    suspend fun getRepoTags(
        @Path("name") name : String,
        @Path("repo") repo : String
    ) : List<Tag>
}
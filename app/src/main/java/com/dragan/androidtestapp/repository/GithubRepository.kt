package com.dragan.androidtestapp.repository

import com.dragan.androidtestapp.data.remote.GithubApi
import com.dragan.androidtestapp.data.remote.responses.RepoDetails
import com.dragan.androidtestapp.data.remote.responses.Repos
import com.dragan.androidtestapp.data.remote.responses.Tag
import com.dragan.androidtestapp.data.remote.responses.User
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import com.dragan.androidtestapp.util.Result

@ActivityScoped
class GithubRepository @Inject constructor(
    private val api: GithubApi
) {

    suspend fun getUser(name: String) : Result<User> {
        val response = try {
            api.getUser(name)
        } catch (e: Exception) {
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response)
    }

    suspend fun getUserRepos(name: String) : Result<Repos> {
        val response = try {
            api.getUserRepos(name)
        } catch (e: Exception) {
            return Result.Error("\"An unknown error occurred")
        }
        return Result.Success(response)
    }

    suspend fun getRepoDetails(name: String, repo: String) : Result<RepoDetails> {
        val response = try {
            api.getRepoDetails(name, repo)
        } catch (e: Exception) {
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response)
    }

    suspend fun getRepoTags(name: String, repo: String) : Result<List<Tag>> {
        val response = try {
            api.getRepoTags(name, repo)
        } catch (e: Exception) {
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response)
    }
}
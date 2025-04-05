package com.dragan.androidtestapp.repository.remote

import com.dragan.androidtestapp.domain.entities.responses.Repo
import com.dragan.androidtestapp.domain.entities.responses.RepoDetails
import com.dragan.androidtestapp.domain.entities.responses.Tag
import com.dragan.androidtestapp.domain.entities.responses.User
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import com.dragan.androidtestapp.domain.Resource
import com.dragan.androidtestapp.repository.GithubApi

@ActivityScoped
class GithubApiImpl @Inject constructor(
    private val api: GithubApi
) {

    suspend fun getUser(name: String) : Resource<User> {
        val response = try {
            api.getUser(name)
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getUserRepos(name: String) : Resource<List<Repo>> {
        val response = try {
            api.getUserRepos(name)
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getRepoDetails(name: String, repo: String) : Resource<RepoDetails> {
        val response = try {
            api.getRepoDetails(name, repo)
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getRepoTags(name: String, repo: String) : Resource<List<Tag>> {
        val response = try {
            api.getRepoTags(name, repo)
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}
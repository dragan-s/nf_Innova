package com.dragan.androidtestapp.presentation.user_repos_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragan.androidtestapp.domain.entities.responses.Repo
import com.dragan.androidtestapp.domain.entities.responses.User
import com.dragan.androidtestapp.repository.remote.GithubApiImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dragan.androidtestapp.domain.Resource
import com.dragan.androidtestapp.util.Constants.TEST_USERNAME

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val repository: GithubApiImpl
) : ViewModel() {

    val user = mutableStateOf<User?>(null)
    var reposList = mutableStateOf<List<Repo>>(listOf())

    init {
        viewModelScope.launch {
            getUser(TEST_USERNAME)
        }

    }

    suspend fun getUser(name: String) : Resource<User> {
        val result = repository.getUser(name)
        when (result) {
            is Resource.Success -> {
                println("#### result.data?.name | ${result.data?.login}")
                if (!result.data?.name.isNullOrEmpty()) {
                    user.value = result.data
                    getUserRepos(result.data.login)
                }
            }

            is Resource.Error -> {

            }
            is Resource.Loading -> {

            }
        }
        return result
    }

    fun getUserRepos(name: String) {
        viewModelScope.launch {
            val result = repository.getUserRepos(name)
            println("##### user repos = ${result.data}")
            if (!result.data.isNullOrEmpty()) {
                reposList.value = result.data
            }
        }
    }
}
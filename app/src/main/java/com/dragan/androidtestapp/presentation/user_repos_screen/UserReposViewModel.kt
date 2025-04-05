package com.dragan.androidtestapp.presentation.user_repos_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragan.androidtestapp.data.remote.responses.User
import com.dragan.androidtestapp.repository.GithubRepository
import com.dragan.androidtestapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)

    suspend fun getUser(name: String) : Result<User> {
        val result = repository.getUser(name)
        when (result) {
            is Result.Success -> {
                println("#### result.data?.name | ${result.data?.login}")
                if (!result.data?.name.isNullOrEmpty()) {
                    getUserRepos(result.data.login)
                }
            }

            is Result.Error -> {

            }
            is Result.Loading -> {

            }
        }
        return result
    }

    fun getUserRepos(name: String) {
        viewModelScope.launch {
            val result = repository.getUserRepos(name)
            println("##### user repos = ${result.data}")
        }
    }
}
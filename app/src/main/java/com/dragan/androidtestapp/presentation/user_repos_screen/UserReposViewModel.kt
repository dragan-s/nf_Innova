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

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    val user = mutableStateOf<User?>(null)
    var reposList = mutableStateOf<List<Repo>>(listOf())

    init {
        isLoading.value = true
        getUser(TEST_USERNAME)
    }

    fun getUser(name: String)  {
        viewModelScope.launch {
            val result = repository.getUser(name)
            when (result) {
                is Resource.Success -> {
                    if (!result.data?.login.isNullOrEmpty()) {
                        isLoading.value = false
                        loadError.value = ""
                        user.value = result.data
                        getUserRepos(result.data.login)
                    }
                }
                is Resource.Error -> {
                    isLoading.value = false
                    loadError.value = result.message.toString()
                }
                is Resource.Loading -> {}
            }
        }
    }

    fun getUserRepos(name: String) {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getUserRepos(name)
            when (result) {
                is Resource.Success -> {
                    if (!result.data.isNullOrEmpty()) {
                        isLoading.value = false
                        loadError.value = ""
                        reposList.value = result.data
                    }
                }
                is Resource.Error -> {
                    isLoading.value = false
                    loadError.value = result.message.toString()
                }
                is Resource.Loading -> {}
            }
        }
    }
}
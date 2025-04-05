package com.dragan.androidtestapp.presentation.repo_details_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragan.androidtestapp.domain.entities.responses.RepoDetails
import com.dragan.androidtestapp.domain.entities.responses.Tag
import com.dragan.androidtestapp.repository.remote.GithubApiImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dragan.androidtestapp.domain.Resource

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: GithubApiImpl,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val userName: String = checkNotNull(savedStateHandle["userName"])
    val repoName: String = checkNotNull(savedStateHandle["repoName"])

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    var tagsList = mutableStateOf<List<Tag>>(listOf())
    val repoDetails = mutableStateOf<RepoDetails?>(null)

    init {
        isLoading.value = true
        getRepoDetails()
        getTags()
    }

    fun getRepoDetails(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getRepoDetails(userName, repoName)
            when (result) {
                is Resource.Success -> {
                    isLoading.value = false
                    loadError.value = ""
                    repoDetails.value = result.data
                }
                is Resource.Error -> {
                    isLoading.value = false
                    loadError.value = result.message.toString()
                }
                is Resource.Loading -> {}
            }
        }
    }

    fun getTags() {
        println("#### tags | username = $userName | repo name = $repoName")
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getRepoTags(userName, repoName)
            when (result) {
                is Resource.Success -> {
                    if (!result.data.isNullOrEmpty()) {
                        isLoading.value = false
                        loadError.value = ""
                        tagsList.value = result.data
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
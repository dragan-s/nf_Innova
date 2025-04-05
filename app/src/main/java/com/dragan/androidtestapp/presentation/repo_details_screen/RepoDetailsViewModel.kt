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

    var tagsList = mutableStateOf<List<Tag>>(listOf())
    val repoDetails = mutableStateOf<RepoDetails?>(null)

    init {
        viewModelScope.launch {
            getRepoDetails(userName, repoName)
            getTags(userName, repoName)
        }
    }


    suspend fun getRepoDetails(name: String, repo: String): Resource<RepoDetails> {
        val result = repository.getRepoDetails(name, repo = repo)
        repoDetails.value = result.data
        return result
    }

    suspend fun getTags(userName: String, repoName: String) {
        println("#### tags | username = $userName | repo name = $repoName")
        val result = repository.getRepoTags(userName, repoName)
        if (!result.data.isNullOrEmpty()) {
            tagsList.value = result.data
        }

    }
}
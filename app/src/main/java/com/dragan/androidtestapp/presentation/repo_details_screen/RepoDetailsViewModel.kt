package com.dragan.androidtestapp.presentation.repo_details_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragan.androidtestapp.data.remote.responses.RepoDetails
import com.dragan.androidtestapp.data.remote.responses.Tag
import com.dragan.androidtestapp.repository.GithubRepository
import com.dragan.androidtestapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: GithubRepository,
) : ViewModel() {

    var tagsList = mutableStateOf<List<Tag>>(listOf())


    suspend fun getRepoDetails(name: String, repo: String): Result<RepoDetails> {
        return repository.getRepoDetails(name, repo = repo)
    }

    fun getTags(userName: String, repoName: String) {
        viewModelScope.launch {
            println("#### tags | username = $userName | repo name = $repoName")
            val result = repository.getRepoTags(userName, repoName)
            if (!result.data.isNullOrEmpty()) {
                tagsList.value = result.data
            }
        }

    }
}
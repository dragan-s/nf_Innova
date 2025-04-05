package com.dragan.androidtestapp.presentation.repo_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragan.androidtestapp.data.remote.responses.RepoDetails
import com.dragan.androidtestapp.repository.GithubRepository
import com.dragan.androidtestapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: GithubRepository,
) : ViewModel() {


    suspend fun getRepoDetails(name: String, repo: String): Result<RepoDetails> {
        return repository.getRepoDetails(name, repo = repo)
    }
}
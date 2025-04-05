package com.dragan.androidtestapp.presentation.repo_details_screen

import androidx.lifecycle.ViewModel
import com.dragan.androidtestapp.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel@Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

}
package com.dragan.androidtestapp.presentation.repo_details_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragan.androidtestapp.data.remote.responses.RepoDetails
import com.dragan.androidtestapp.util.Result

@Composable
fun RepoDetailsScreen(
    userName: String,
    repoName: String,
    viewModel: RepoDetailsViewModel = hiltViewModel()
) {

    val repoDetails = produceState<Result<RepoDetails>>(
        initialValue = Result.Loading()
    ) {
        value = viewModel.getRepoDetails(userName, repoName)
    }.value

    Text(text ="Name : ${repoDetails.data?.name}")

}

@Composable
fun RepoDetailsHeader() {



}
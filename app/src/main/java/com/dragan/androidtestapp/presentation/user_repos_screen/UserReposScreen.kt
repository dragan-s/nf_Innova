package com.dragan.androidtestapp.presentation.user_repos_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragan.androidtestapp.data.remote.responses.Repo
import com.dragan.androidtestapp.data.remote.responses.User
import com.dragan.androidtestapp.util.Constants.TEST_USERNAME
import com.dragan.androidtestapp.domain.Result

@Composable
fun UserReposScreen(
    navController: NavController,
    viewModel: UserReposViewModel = hiltViewModel(),
) {

    val user = produceState<Result<User>>(
        initialValue = Result.Loading()
    ) {
        value = viewModel.getUser(TEST_USERNAME)
    }.value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "User : ${user.data?.name}"
            )

            Spacer(modifier = Modifier.height(25.dp))

            ReposList(navController = navController, user.data?.login)
        }
    }

}

@Composable
fun ReposList(
    navController: NavController,
    userName: String?,
    viewModel: UserReposViewModel = hiltViewModel(),
) {
    val reposList by remember { viewModel.reposList }

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(reposList) { repoItem ->
            RepoRowItem(repoItem, onClick = {
                navController.navigate("repo_details_screen/${userName}/${repoItem.name}")
            })
        }
    }
}

@Composable
fun RepoRowItem(repoItem: Repo, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = repoItem.name,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(5.dp))

        Text(text = "Opened issues: ${repoItem.open_issues}")
    }
}
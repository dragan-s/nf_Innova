package com.dragan.androidtestapp.presentation.user_repos_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragan.androidtestapp.data.remote.responses.User
import com.dragan.androidtestapp.util.Constants.TEST_USERNAME
import com.dragan.androidtestapp.util.Result

@Composable
fun UserReposScreen(
    navController: NavController,
    viewModel: UserReposViewModel = hiltViewModel()
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

            Text(text = "User : ${user.data?.name}")

            Spacer(modifier = Modifier.height(25.dp))

            //ReposList(navController = navController)
        }
    }
    
}

@Composable
fun ReposList(
    navController: NavController,
    viewModel: UserReposViewModel = hiltViewModel()
    ) {


}
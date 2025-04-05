package com.dragan.androidtestapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dragan.androidtestapp.presentation.repo_details_screen.RepoDetailsScreen
import com.dragan.androidtestapp.presentation.user_repos_screen.UserReposScreen

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = "user_repos_screen",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("user_repos_screen") {
            UserReposScreen(navController = navController)
        }
        composable("repo_details_screen") {
            RepoDetailsScreen()
        }
    }
}
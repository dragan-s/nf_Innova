package com.dragan.androidtestapp.presentation.repo_details_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun RepoDetailsScreen(
    viewModel: RepoDetailsViewModel = hiltViewModel()
) {

    val repoDetails by remember { viewModel.repoDetails }
    val tagsList by remember { viewModel.tagsList }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            RepoDetailsHeader(
                avatarUrl = repoDetails?.owner?.avatar_url ?: "",
                userName = repoDetails?.owner?.login ?: "",
                repoName = repoDetails?.name ?: "",
                watchers = repoDetails?.watchers_count ?: -1,
                forks = repoDetails?.forks ?: -1
            )

            Spacer(modifier = Modifier.height(10.dp))

            val tagText =
                if (tagsList.isNotEmpty()) {
                    "Repo tags"
                } else {
                    "No tags for this repository"
                }


            Text(
                text = tagText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(tagsList) {tag ->
            TagItem(
                tagName = tag.name,
                sha = tag.commit.sha
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun RepoDetailsHeader(
    avatarUrl: String,
    userName: String,
    repoName: String,
    watchers: Int,
    forks: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = userName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = repoName,
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Watchers: $watchers")
                Text(text = "Forks: $forks")
            }
        }
    }
}

@Composable
fun TagItem(tagName: String, sha: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = tagName, fontWeight = FontWeight.Bold)
        Text(text = "SHA: $sha", fontSize = 12.sp, color = Color.Gray)
    }
}
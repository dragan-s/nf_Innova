package com.dragan.androidtestapp.data.remote.responses

data class Tag(
    val commit: Commit,
    val name: String,
    val node_id: String,
    val tarball_url: String,
    val zipball_url: String
)
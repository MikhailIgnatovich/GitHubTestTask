package com.bulich.misha.githubtesttask.data.model

import com.google.gson.annotations.SerializedName

data class GitHubResponse(

    @SerializedName("id")
    val id: Int,
    @SerializedName("full_name")
    val nameRepository: String,
    @SerializedName("owner")
    val owner: OwnerResponse,
    @SerializedName("commits_url")
    val commitsUrl: String
)

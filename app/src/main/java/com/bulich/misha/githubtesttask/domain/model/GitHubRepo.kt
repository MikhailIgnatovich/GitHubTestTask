package com.bulich.misha.githubtesttask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepo(
    val id: Int,
    val nameRepository: String,
    val ownerModel: Owner,
    val commitsUrl: String
): Parcelable
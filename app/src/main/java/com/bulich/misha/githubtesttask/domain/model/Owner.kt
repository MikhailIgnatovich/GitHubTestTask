package com.bulich.misha.githubtesttask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val ownerAvatar: String,
    val ownerLogin: String
): Parcelable

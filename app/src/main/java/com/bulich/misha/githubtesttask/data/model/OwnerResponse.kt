package com.bulich.misha.githubtesttask.data.model

import com.google.gson.annotations.SerializedName

data class OwnerResponse(

    @SerializedName("avatar_url")
    val ownerAvatar: String,
    @SerializedName("login")
    val ownerLogin: String
)

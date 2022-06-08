package com.bulich.misha.githubtesttask.data.model

import com.google.gson.annotations.SerializedName

data class AuthorResponse(

    @SerializedName("name")
    val name: String,
    @SerializedName("date")
    val data: String
)

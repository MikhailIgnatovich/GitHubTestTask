package com.bulich.misha.githubtesttask.data.model

import com.google.gson.annotations.SerializedName

data class CommitResponse(

    @SerializedName("author")
    val author: AuthorResponse,
    val message: String
)

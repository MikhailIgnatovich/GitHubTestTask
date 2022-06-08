package com.bulich.misha.githubtesttask.data.model

import com.google.gson.annotations.SerializedName

data class CommitsResponse(

    @SerializedName("commit")
    val commit: CommitResponse,
    @SerializedName("parents")
    val parents: List<ParentsResponse>
)
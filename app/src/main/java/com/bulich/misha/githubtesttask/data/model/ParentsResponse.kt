package com.bulich.misha.githubtesttask.data.model

import com.google.gson.annotations.SerializedName

data class ParentsResponse(

    @SerializedName("sha")
    val sha: String
)

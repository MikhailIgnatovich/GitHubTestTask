package com.bulich.misha.githubtesttask.domain.model

data class Commit(

    val authorName: String,
    val date: String,
    val message: String,
    val shaList: List<String>
)

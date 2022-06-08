package com.bulich.misha.githubtesttask.data.api

import com.bulich.misha.githubtesttask.data.model.CommitsResponse
import com.bulich.misha.githubtesttask.data.model.GitHubResponse
import retrofit2.Response
import retrofit2.http.*

interface GitHubService {


    @GET("repositories")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun getListRepositories(@Query("since") since: Int = 1)
            : Response<List<GitHubResponse>>


    @GET
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun getListCommitsResponse(@Url commit: String = "")
            : Response<List<CommitsResponse>>
}
package com.bulich.misha.githubtesttask.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo

interface GetGitHubPublicRepository {

    fun getListRepository(): LiveData<PagingData<GitHubRepo>>
}
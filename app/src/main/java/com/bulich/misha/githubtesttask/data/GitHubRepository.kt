package com.bulich.misha.githubtesttask.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.bulich.misha.githubtesttask.data.api.GitHubService
import com.bulich.misha.githubtesttask.data.repository.GitHubPagingSource
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo
import com.bulich.misha.githubtesttask.domain.repository.GetGitHubPublicRepository


class GitHubRepository(private val api: GitHubService): GetGitHubPublicRepository {

    override fun getListRepository(): LiveData<PagingData<GitHubRepo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GitHubPagingSource(api)}

        ).liveData
    }
}
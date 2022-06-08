package com.bulich.misha.githubtesttask.domain.useCase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo
import com.bulich.misha.githubtesttask.domain.repository.GetGitHubPublicRepository
import kotlinx.coroutines.flow.Flow

class GetGitHubRepositoriesUseCase(private val getGitHubPublicRepository: GetGitHubPublicRepository) {

    fun getListRepositories(): LiveData<PagingData<GitHubRepo>> {
        return getGitHubPublicRepository.getListRepository()
    }
}
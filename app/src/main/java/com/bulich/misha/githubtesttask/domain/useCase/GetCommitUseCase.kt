package com.bulich.misha.githubtesttask.domain.useCase

import com.bulich.misha.githubtesttask.domain.model.Commit
import com.bulich.misha.githubtesttask.domain.repository.GetCommitRepository
import com.bulich.misha.githubtesttask.domain.utils.Resource

class GetCommitUseCase(private val getCommitRepository: GetCommitRepository) {

    suspend fun getCommit(url: String): Resource<Commit> {
        return getCommitRepository.getCommit(url)
    }
}
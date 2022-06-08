package com.bulich.misha.githubtesttask.domain.repository

import com.bulich.misha.githubtesttask.domain.model.Commit
import com.bulich.misha.githubtesttask.domain.utils.Resource

interface GetCommitRepository {

    suspend fun getCommit(url: String): Resource<Commit>
}
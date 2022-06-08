package com.bulich.misha.githubtesttask.data.repository

import com.bulich.misha.githubtesttask.data.api.GitHubService
import com.bulich.misha.githubtesttask.data.model.CommitsResponse
import com.bulich.misha.githubtesttask.domain.model.Commit
import com.bulich.misha.githubtesttask.domain.repository.GetCommitRepository
import com.bulich.misha.githubtesttask.domain.utils.Resource

class GetCommitRepositoryIMPL(private val api: GitHubService) : GetCommitRepository {

    override suspend fun getCommit(url: String): Resource<Commit> {
        return try {
            val response = api.getListCommitsResponse(url)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                val commit = commitsResponseToCommit(result)
                Resource.Success(commit)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error")
        }
    }

    private fun commitsResponseToCommit(commitsResponse: List<CommitsResponse>): Commit {
        val listCommit = mutableListOf<Commit>()
        for (item in commitsResponse) {
            listCommit.add(
                Commit(
                    authorName = item.commit.author.name,
                    date = item.commit.author.data,
                    message = item.commit.message,
                    shaList = getShaList(item)
                )
            )
        }
        return listCommit[0]
    }

    private fun getShaList(commitsResponse: CommitsResponse): List<String> {
        val listSha = mutableListOf<String>()
        for (item in commitsResponse.parents) {
            listSha.add(item.sha)
        }
        return listSha
    }
}
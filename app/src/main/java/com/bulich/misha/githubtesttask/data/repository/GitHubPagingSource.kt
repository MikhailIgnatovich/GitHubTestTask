package com.bulich.misha.githubtesttask.data.repository


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bulich.misha.githubtesttask.data.api.GitHubService
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo
import com.bulich.misha.githubtesttask.domain.model.Owner
import retrofit2.HttpException

class GitHubPagingSource(private val api: GitHubService) : PagingSource<Int, GitHubRepo>() {
    override fun getRefreshKey(state: PagingState<Int, GitHubRepo>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepo> {
        return try {
            val currentPage = params.key ?: 1
            val response = api.getListRepositories(currentPage)
            val repos = response.body()!!
            val listRepositories = mutableListOf<GitHubRepo>()
            for (item in repos) {
                listRepositories.add(
                    GitHubRepo(
                        id = item.id,
                        nameRepository = item.nameRepository,
                        ownerModel = Owner(
                            item.owner.ownerAvatar,
                            item.owner.ownerLogin
                        ),
                        commitsUrl = parseUrl(item.commitsUrl)
                    )
                )
            }
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                val lastIndex = repos.lastIndex
                repos[lastIndex].id
            }
            LoadResult.Page(
                data = listRepositories,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    private fun parseUrl(url: String): String {
        return url.dropLast(6)
    }
}
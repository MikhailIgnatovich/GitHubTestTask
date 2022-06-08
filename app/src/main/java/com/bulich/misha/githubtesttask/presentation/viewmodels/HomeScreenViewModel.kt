package com.bulich.misha.githubtesttask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bulich.misha.githubtesttask.domain.useCase.GetGitHubRepositoriesUseCase
import com.bulich.misha.githubtesttask.domain.useCase.GetInternetStatusUseCase

class HomeScreenViewModel(
    private val getInternetStatusUseCase: GetInternetStatusUseCase,
    private val getGitHubRepositoriesUseCase: GetGitHubRepositoriesUseCase
) : ViewModel() {

    val listData = getGitHubRepositoriesUseCase.getListRepositories().cachedIn(viewModelScope)

    fun getInternetStatus(): Boolean {
        return getInternetStatusUseCase.chekInternetStatus()
    }
}
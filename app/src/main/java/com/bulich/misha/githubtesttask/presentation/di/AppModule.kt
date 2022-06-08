package com.bulich.misha.githubtesttask.presentation.di

import com.bulich.misha.githubtesttask.data.GitHubRepository
import com.bulich.misha.githubtesttask.data.repository.GetCommitRepositoryIMPL
import com.bulich.misha.githubtesttask.domain.repository.GetCommitRepository
import com.bulich.misha.githubtesttask.domain.repository.GetGitHubPublicRepository
import com.bulich.misha.githubtesttask.domain.useCase.GetCommitUseCase
import com.bulich.misha.githubtesttask.domain.useCase.GetGitHubRepositoriesUseCase
import com.bulich.misha.githubtesttask.domain.useCase.GetInternetStatusUseCase
import com.bulich.misha.githubtesttask.presentation.viewmodels.DetailsViewModel
import com.bulich.misha.githubtesttask.presentation.viewmodels.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory<GetGitHubPublicRepository> {
        GitHubRepository(get())
    }

    factory<GetCommitRepository> {
        GetCommitRepositoryIMPL(get())
    }

    factory {
        GetCommitUseCase(get())
    }

    factory {
        GetGitHubRepositoriesUseCase(get())
    }

    factory {
        GetInternetStatusUseCase(get())
    }

    viewModel {
        HomeScreenViewModel(get(), get())
    }

    viewModel {
        DetailsViewModel(get(), get())
    }
}
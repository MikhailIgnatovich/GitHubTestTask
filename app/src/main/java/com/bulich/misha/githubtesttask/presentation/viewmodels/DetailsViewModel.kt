package com.bulich.misha.githubtesttask.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulich.misha.githubtesttask.domain.model.Commit
import com.bulich.misha.githubtesttask.domain.useCase.GetCommitUseCase
import com.bulich.misha.githubtesttask.domain.useCase.GetInternetStatusUseCase
import com.bulich.misha.githubtesttask.domain.utils.Resource
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getCommitUseCase: GetCommitUseCase,
    private val getInternetStatusUseCase: GetInternetStatusUseCase
) : ViewModel() {

    private val _commitData = MutableLiveData<Commit>()
    val commitData: LiveData<Commit>
        get() = _commitData

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean>
        get() = _loadingStatus

    var internetStatus = true

    var errorServer = ""

    init {
        _loadingStatus.value = true
    }

    fun getCommit(url: String) {
        viewModelScope.launch {
            internetStatus = getInternetStatusUseCase.chekInternetStatus()
            _loadingStatus.value = internetStatus
            if (internetStatus) {
                when (val response = getCommitUseCase.getCommit(url)) {
                    is Resource.Success -> {
                        _commitData.value = response.data!!
                        _loadingStatus.value = false
                    }
                    is Resource.Error -> {
                        errorServer = response.message.toString()
                        _loadingStatus.value = false
                    }
                }
            }
        }
    }
}
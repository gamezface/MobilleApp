package com.gamezface.mobileapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamezface.domain.state.ViewState
import com.gamezface.mobileapp.extensions.postError
import com.gamezface.mobileapp.extensions.postLoading
import com.gamezface.mobileapp.extensions.postSuccess
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    suspend fun <T> handleWork(
        work: suspend () -> T,
        stateLiveData: MutableLiveData<ViewState<T>>
    ) {
        stateLiveData.postLoading()
        viewModelScope.launch {
            val result =
                runCatching { work() }
            result.onSuccess { response ->
                stateLiveData.postSuccess(response)
            }.onFailure { throwable ->
                stateLiveData.postError(throwable)
            }
        }
    }
}
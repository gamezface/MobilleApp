package com.gamezface.mobileapp.extensions

import androidx.lifecycle.MutableLiveData
import com.gamezface.domain.state.ViewState

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    this.postValue(ViewState.success(data))
}

fun <T> MutableLiveData<ViewState<T>>.postError(throwable: Throwable) {
    this.postValue(ViewState.error(throwable))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    this.postValue(ViewState.loading())
}
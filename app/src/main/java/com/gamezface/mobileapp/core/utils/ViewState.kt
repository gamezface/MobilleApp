package com.gamezface.mobileapp.core.utils

import androidx.lifecycle.MutableLiveData

class ViewState<D>(
    val status: ViewStatus,
    val data: D? = null,
    val throwable: Throwable? = null
) {
    fun handleIt(
        success: (D?) -> Unit = {},
        error: (Throwable?) -> Unit = {},
        loading: () -> Unit = {},
        stopLoading: () -> Unit = {}
    ): ViewState<D> {
        when (this.status) {
            ViewStatus.LOADING -> {
                loading()
            }
            ViewStatus.SUCCESS -> {
                success(data); stopLoading()
            }
            ViewStatus.ERROR -> {
                error(throwable); stopLoading()
            }
            ViewStatus.FINISHED -> {
                stopLoading()
            }
        }

        return this
    }

    companion object {
        fun <T> success(data: T): ViewState<T> {
            return ViewState(status = ViewStatus.SUCCESS, data = data)
        }

        fun <T> error(throwable: Throwable): ViewState<T> {
            return ViewState(status = ViewStatus.ERROR, throwable = throwable)
        }
    }

    enum class ViewStatus {
        LOADING, SUCCESS, ERROR, FINISHED
    }
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    this.postValue(ViewState(status = ViewState.ViewStatus.SUCCESS, data = data))
}

fun <T> MutableLiveData<ViewState<T>>.postError(throwable: Throwable) {
    this.postValue(ViewState(status = ViewState.ViewStatus.ERROR, throwable = throwable))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    this.postValue(ViewState(status = ViewState.ViewStatus.LOADING))
}
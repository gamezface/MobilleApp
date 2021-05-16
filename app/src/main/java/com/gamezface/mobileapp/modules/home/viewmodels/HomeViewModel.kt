package com.gamezface.mobileapp.modules.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamezface.mobileapp.core.utils.ViewState
import com.gamezface.mobileapp.core.utils.postError
import com.gamezface.mobileapp.core.utils.postLoading
import com.gamezface.mobileapp.core.utils.postSuccess
import com.gamezface.mobileapp.modules.home.models.Location
import com.gamezface.mobileapp.modules.home.models.Locations
import com.gamezface.mobileapp.modules.home.repository.HomeRepository
import com.gamezface.mobileapp.modules.home.repository.ImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel
@Inject constructor(
    private val repository: HomeRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _locationsLiveData = MutableLiveData<ViewState<Locations>>()
    fun getLocations(): LiveData<ViewState<Locations>> = _locationsLiveData

    val locations = mutableListOf<Location>()

    fun loadLocations() {
        locations.clear()
        viewModelScope.launch {
            _locationsLiveData.postLoading()
            val result = runCatching { repository.requestLocations() }
            result.onSuccess { response ->
                loadImages(response)
            }.onFailure { throwable ->
                _locationsLiveData.postError(throwable)
            }
        }
    }

    private fun loadImages(response: Locations) {
        viewModelScope.launch {
            val imageResult =
                runCatching { imageRepository.requestImages(response.listLocations.size) }
            imageResult.onSuccess { images ->
                response.listLocations.forEachIndexed { index, location ->
                    location.imageUrl = images[index].downloadUrl
                }
                _locationsLiveData.postSuccess(response)
            }.onFailure {
                _locationsLiveData.postSuccess(response)
            }
        }
    }
}
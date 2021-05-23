package com.gamezface.mobileapp.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.gamezface.domain.images.entity.Image
import com.gamezface.domain.images.repository.ImageRepository
import com.gamezface.domain.location.entity.Location
import com.gamezface.domain.location.repository.LocationRepository
import com.gamezface.domain.state.ViewState
import com.gamezface.mobileapp.base.BaseViewModel
import com.gamezface.mobileapp.base.SingleLiveEvent

class HomeViewModel
@ViewModelInject constructor(
    private val repository: LocationRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel() {
    private val _locationsLiveData = SingleLiveEvent<ViewState<List<Location>>>()
    fun getLocations(): LiveData<ViewState<List<Location>>> = _locationsLiveData

    private val _imageListLiveData = SingleLiveEvent<ViewState<List<Image>>>()
    fun getImages(): LiveData<ViewState<List<Image>>> = _imageListLiveData

    val locations = mutableListOf<Location>()

    suspend fun loadLocations() {
        locations.clear()
        handleWork({ repository.retrieveLocations() }, _locationsLiveData)
    }

    suspend fun handleLocationSuccess(response: List<Location>) {
        locations.addAll(response)
        loadImages(response)
    }

    private suspend fun loadImages(response: List<Location>) {
        handleWork({ imageRepository.retrieveImages(response.size) }, _imageListLiveData)
    }

    fun handleLoadedImages(images: List<Image>?) {
        images?.let {
            locations.forEachIndexed { index, location ->
                location.imageUrl = images[index].downloadUrl
            }
        }
    }
}
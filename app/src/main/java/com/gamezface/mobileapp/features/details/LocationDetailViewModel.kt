package com.gamezface.mobileapp.features.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gamezface.domain.images.entity.Image
import com.gamezface.domain.images.repository.ImageRepository
import com.gamezface.domain.location.entity.Location
import com.gamezface.domain.location.repository.LocationRepository
import com.gamezface.domain.state.ViewState
import com.gamezface.mobileapp.base.BaseViewModel


class LocationDetailViewModel
@ViewModelInject constructor(
    private val repository: LocationRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel() {
    private val _locationLiveData = MutableLiveData<ViewState<Location>>()
    fun getLocation(): LiveData<ViewState<Location>> = _locationLiveData

    private val _imageListLiveData = MutableLiveData<ViewState<List<Image>>>()
    fun getImages(): LiveData<ViewState<List<Image>>> = _imageListLiveData

    var id: Long = 0
    var imageUrl: String? = null

    suspend fun loadLocation(id: Long) {
        handleWork({ repository.retrieveLocation(id) }, _locationLiveData)
    }

    suspend fun loadImages() {
        handleWork({ imageRepository.retrieveImages(5) }, _imageListLiveData)
    }
}
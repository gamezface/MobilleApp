package com.gamezface.mobileapp.modules.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamezface.mobileapp.core.utils.*
import com.gamezface.mobileapp.modules.home.models.Image
import com.gamezface.mobileapp.modules.home.models.Location
import com.gamezface.mobileapp.modules.home.models.Schedules
import com.gamezface.mobileapp.modules.home.repository.HomeRepository
import com.gamezface.mobileapp.modules.home.repository.ImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class LocationDetailViewModel
@Inject constructor(
    private val repository: HomeRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _locationsLiveData = MutableLiveData<ViewState<Location>>()
    fun getLocation(): LiveData<ViewState<Location>> = _locationsLiveData

    private val _imageListLiveData = MutableLiveData<ViewState<List<Image>>>()
    fun getImages(): LiveData<ViewState<List<Image>>> = _imageListLiveData

    var id: Long = 0
    var imageUrl: String? = null

    fun loadLocation(id: Long) {
        _locationsLiveData.postLoading()
        viewModelScope.launch {
            val result = runCatching { repository.requestLocation(id) }
            result.onSuccess { response ->
                _locationsLiveData.postSuccess(response)
            }.onFailure { throwable ->
                _locationsLiveData.postError(throwable)
            }
        }
    }

    fun loadImages() {
        viewModelScope.launch {
            val imageResult =
                runCatching { imageRepository.requestImages(5) }
            imageResult.onSuccess { images ->
                _imageListLiveData.postSuccess(images)
            }.onFailure { throwable ->
                _imageListLiveData.postError(throwable)
            }
        }
    }

    fun handleScheduleText(schedule: Any): Schedules? {
        return if (schedule is List<*>) {
            GsonUtil().fromJson<Schedules>(schedule[0].toString())
        } else {
            GsonUtil().fromJson<Schedules>(schedule.toString())
        }
    }

}
package com.gamezface.mobileapp.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamezface.domain.location.entity.Location
import com.gamezface.domain.location.entity.mapSchedules
import com.gamezface.domain.state.ViewState
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.databinding.FragmentLocationDetailBinding
import com.gamezface.mobileapp.extensions.hide
import com.gamezface.mobileapp.extensions.loadImage
import com.gamezface.mobileapp.extensions.show
import com.gamezface.mobileapp.extensions.showErrorModal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val ID = "ID"
const val IMAGE_URL = "IMAGE_URL"

@AndroidEntryPoint
class LocationDetailFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentLocationDetailBinding

    private val locationDetailViewModel: LocationDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initListeners()
    }

    private fun initView() {
        arguments?.let {
            locationDetailViewModel.id = it.getLong(ID).or(0)
            locationDetailViewModel.imageUrl = it.getString(IMAGE_URL).orEmpty()
        }
        loadLocation()
        binding.locationImageView.loadImage(requireContext(), locationDetailViewModel.imageUrl)
    }

    private fun initListeners() {
        binding.returnImageButton.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initObservers() {
        locationDetailViewModel.getLocation().observe(viewLifecycleOwner, { state ->
            handleLocationDetailsState(state)
        })
        locationDetailViewModel.getImages().observe(viewLifecycleOwner, { state ->
            state?.handleIt(success = {
                binding.contentInfoInclude.locationPicturesRecyclerView.apply {
                    adapter = ImagesAdapter(it)
                    layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }
            })
        })
    }

    private fun handleLocationDetailsState(state: ViewState<Location>?) {
        state?.handleIt(
            loading = { binding.progressBar.show() },
            stopLoading = { binding.progressBar.hide() },
            error = { onErrorState(state.throwable) },
            success = { onSuccessState(state.data) }
        )
    }

    private fun onSuccessState(data: Location?) {
        data?.let { location ->
            binding.contentInfoInclude.apply {
                locationNameTextView.text = location.name
                locationReviewRatingBar.rating = location.review
                locationReviewValueTextView.text = location.review.toString()
                aboutLocationTextView.text = location.about
                scheduleTextView.text =
                    location.schedule?.mapSchedules(
                        resources.getStringArray(R.array.weekdays_array),
                        getString(R.string.to)
                    )
                phoneTextView.text = location.phone
                addressTextView.text = location.address
            }
        }
    }

    private fun onErrorState(throwable: Throwable?) {
        showErrorModal(throwable = throwable, action = { _, _ -> loadLocation() })
    }

    private fun loadLocation() {
        launch {
            locationDetailViewModel.loadLocation(locationDetailViewModel.id)
            locationDetailViewModel.loadImages()
        }
    }
}
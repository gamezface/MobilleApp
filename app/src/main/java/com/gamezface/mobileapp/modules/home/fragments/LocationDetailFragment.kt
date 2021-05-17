package com.gamezface.mobileapp.modules.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamezface.mobileapp.core.utils.*
import com.gamezface.mobileapp.databinding.FragmentLocationDetailBinding
import com.gamezface.mobileapp.modules.home.activity.HomeActivity
import com.gamezface.mobileapp.modules.home.adapters.ImagesAdapter
import com.gamezface.mobileapp.modules.home.models.Location
import com.gamezface.mobileapp.modules.home.viewmodels.LocationDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

const val ID = "ID"
const val IMAGE_URL = "IMAGE_URL"

class LocationFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentLocationDetailBinding

    @Inject
    lateinit var locationDetailViewModel: LocationDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initObservers()
        initListeners()
    }

    private fun initView() {
        arguments?.let {
            locationDetailViewModel.id = it[ID] as? Long ?: 0
            locationDetailViewModel.imageUrl = it[IMAGE_URL] as? String ?: ""
        }
        loadLocation()
        binding.locationImageView.loadImage(requireContext(), locationDetailViewModel.imageUrl)
    }

    private fun initListeners() {
        binding.returnImageButton.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initObservers() {
        locationDetailViewModel.getLocation().observe(viewLifecycleOwner, Observer { state ->
            handleLocationDetailsState(state)
        })
        locationDetailViewModel.getImages().observe(viewLifecycleOwner, Observer { state ->
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
                scheduleTextView.text = mapSchedule(locationDetailViewModel.handleScheduleText(location.schedule))
                phoneTextView.text = location.phone
                addressTextView.text = location.adress
            }
        }
    }

    private fun onErrorState(throwable: Throwable?) {
        showModalError(throwable = throwable, action = { _, _ -> loadLocation() })
    }

    private fun loadLocation() {
        locationDetailViewModel.loadLocation(locationDetailViewModel.id)
        locationDetailViewModel.loadImages()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HomeActivity).homeComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}
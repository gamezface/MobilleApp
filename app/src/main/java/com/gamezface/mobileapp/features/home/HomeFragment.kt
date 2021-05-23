package com.gamezface.mobileapp.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gamezface.domain.images.entity.Image
import com.gamezface.domain.location.entity.Location
import com.gamezface.domain.state.ViewState
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.databinding.FragmentHomeBinding
import com.gamezface.mobileapp.extensions.hide
import com.gamezface.mobileapp.extensions.show
import com.gamezface.mobileapp.extensions.showErrorModal
import com.gamezface.mobileapp.features.details.ID
import com.gamezface.mobileapp.features.details.IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    private val locationsAdapter by lazy {
        LocationsAdapter(
            homeViewModel.locations,
            ::handleLocationListener
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadLocations()
        initObservers()
        initView()
    }

    private fun initView() {
        binding.locationsRecyclerView.apply {
            adapter = locationsAdapter
            layoutManager = StaggeredGridLayoutManager(
                resources.getInteger(R.integer.grid_columns), VERTICAL
            )
        }
    }

    private fun loadLocations() {
        launch {
            homeViewModel.loadLocations()
        }
    }

    private fun initObservers() {
        homeViewModel.getLocations().observe(viewLifecycleOwner, { state ->
            handleLocationsResult(state)
        })
        homeViewModel.getImages().observe(viewLifecycleOwner, { state ->
            handleImagesResult(state)
        })
    }

    private fun handleImagesResult(state: ViewState<List<Image>>?) {
        state?.handleIt(
            success = { homeViewModel.handleLoadedImages(it); locationsAdapter.notifyDataSetChanged() },
        )
    }

    private fun handleLocationsResult(state: ViewState<List<Location>>?) {
        state?.handleIt(
            loading = { binding.progressBar.show() },
            stopLoading = { binding.progressBar.hide() },
            success = { onSuccessState(state.data) },
            error = { onErrorState(state.throwable) },
        )
    }

    private fun onErrorState(throwable: Throwable?) {
        showErrorModal(throwable = throwable, action = { _, _ -> loadLocations() })
    }

    private fun onSuccessState(data: List<Location>?) {
        data?.let { locations ->
            launch {
                homeViewModel.handleLocationSuccess(locations)
            }
            locationsAdapter.notifyDataSetChanged()
        }
    }

    private fun handleLocationListener(id: Long, imageUrl: String?) {
        findNavController().navigate(
            R.id.action_homeFragment_to_locationDetailFragment,
            bundleOf(ID to id, IMAGE_URL to imageUrl)
        )
    }
}
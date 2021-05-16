package com.gamezface.mobileapp.modules.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.core.utils.ViewState
import com.gamezface.mobileapp.core.utils.hide
import com.gamezface.mobileapp.core.utils.show
import com.gamezface.mobileapp.core.utils.showModalError
import com.gamezface.mobileapp.databinding.FragmentHomeBinding
import com.gamezface.mobileapp.modules.home.activity.HomeActivity
import com.gamezface.mobileapp.modules.home.adapters.LocationsAdapter
import com.gamezface.mobileapp.modules.home.models.Locations
import com.gamezface.mobileapp.modules.home.viewmodels.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadLocations()
        initObservers()
        initView()
        initListeners()
    }

    private fun initView() {
        binding.locationsRecyclerView.apply {
            adapter = locationsAdapter
            layoutManager = StaggeredGridLayoutManager(
                resources.getInteger(R.integer.grid_columns), VERTICAL
            )
        }
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            loadLocations()
            locationsAdapter.notifyDataSetChanged()
        }
    }

    private fun loadLocations() {
        launch {
            homeViewModel.loadLocations()
        }
    }

    private fun initObservers() {
        homeViewModel.getLocations().observe(viewLifecycleOwner, Observer { state ->
            handleLocationsResult(state)
        })
    }

    private fun handleLocationsResult(state: ViewState<Locations>?) {
        state?.handleIt(
            loading = { binding.progressBar.show() },
            stopLoading = {
                binding.progressBar.hide()
                binding.swipeRefreshLayout.isRefreshing = false
            },
            success = { onSuccessState(state.data) },
            error = { onErrorState(state.throwable) },
        )
    }

    private fun onErrorState(throwable: Throwable?) {
        showModalError(throwable = throwable, action = { _, _ -> loadLocations() })
    }

    private fun onSuccessState(data: Locations?) {
        data?.let {
            homeViewModel.locations.addAll(data.listLocations)
            locationsAdapter.notifyDataSetChanged()
        }
    }

    private fun handleLocationListener(id: Long, imageUrl: String?) {
        findNavController().navigate(
            R.id.action_homeFragment_to_locationDetailFragment,
            bundleOf(ID to id, IMAGE_URL to imageUrl)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HomeActivity).homeComponent.inject(this)
    }
}
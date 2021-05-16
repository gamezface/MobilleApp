package com.gamezface.mobileapp.modules.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gamezface.mobileapp.databinding.FragmentLocationDetailBinding
import com.gamezface.mobileapp.modules.home.activity.HomeActivity
import com.gamezface.mobileapp.modules.home.viewmodels.HomeViewModel
import javax.inject.Inject

const val ID = "ID"
const val IMAGE_URL = "IMAGE_URL"

class LocationFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

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
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HomeActivity).homeComponent.inject(this)
    }
}
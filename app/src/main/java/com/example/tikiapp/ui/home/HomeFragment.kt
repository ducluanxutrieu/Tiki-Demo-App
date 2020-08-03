package com.example.tikiapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tikiapp.R
import com.example.tikiapp.databinding.HomeFragmentBinding
import com.smarteist.autoimageslider.SliderAnimations

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel = HomeViewModel()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupBinding(inflater, container)
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        // TODO: Use the ViewModel
    }

    private fun setupRecyclerView() {
        binding.imageSlider.setSliderAdapter(viewModel.bannerAdapter)
        binding.imageSlider.startAutoCycle()
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)

        //quick link
        val layoutManager = GridLayoutManager(binding.root.context, 2, LinearLayoutManager.HORIZONTAL, false)
        binding.rvQuickLink.layoutManager = layoutManager
        binding.rvQuickLink.adapter = viewModel.quickLinkAdapter
    }

}